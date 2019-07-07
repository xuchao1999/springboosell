package com.xc.sell.service.impl;

import com.xc.sell.dataobject.OrderDetail;
import com.xc.sell.dataobject.OrderMaster;
import com.xc.sell.dataobject.ProductInfo;
import com.xc.sell.dto.OrderDTO;
import com.xc.sell.enums.OrderStatusEnum;
import com.xc.sell.enums.PayStatusEnum;
import com.xc.sell.enums.ResultEnum;
import com.xc.sell.exception.SellsException;
import com.xc.sell.repository.OrderDetailRepository;
import com.xc.sell.repository.OrderMasterRepository;
import com.xc.sell.service.AddressService;
import com.xc.sell.service.OrderMasterService;
import com.xc.sell.service.ProductInfoService;
import com.xc.sell.service.UserInfoService;
import com.xc.sell.util.DateUtil;
import com.xc.sell.util.KeyUtil;
import com.xc.sell.util.ResultVOUtil;
import com.xc.sell.viewObject.OrderVO;
import com.xc.sell.viewObject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AddressService addressService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();
        /*查询商品（数量和价格）*/
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        Lock lock = new ReentrantLock();

        /**lock 解决高并发减库存操作
         * 等学了redis后可以用redis解决高并发问题
         * */
        lock.lock();
        try{
            for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
                ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
                if(productInfo == null){
                    throw new SellsException(ResultEnum.PRODUCT_NOT_EXIST);
                }
                /*计算总价*/
                orderAmount = orderAmount.add(productInfo.getProductPrice().multiply(BigDecimal.valueOf(orderDetail.getProductQuantity())));

                /*订单详情入库*/
                orderDetail.setOrderId(orderId);
                orderDetail.setDetailId(KeyUtil.getUniqueKey());
                orderDetailRepository.save(orderDetail);

                /*扣库存*/
                productInfoService.decreaseStock(productInfo.getProductId(), orderDetail.getProductQuantity());
            }
        }finally {
            lock.unlock();
        }


        /*写入订单数据库*/
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO cancel(String openId, String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findByOpenIdAndOrderId(openId, orderId);
        if(orderMaster == null){
            throw new SellsException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if(orderDetailList.size() == 0){
            throw new SellsException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        for(OrderDetail orderDetail : orderDetailList){
            /*返回库存*/
            productInfoService.increaseStock(orderDetail.getProductId(), orderDetail.getProductQuantity());
            /*删除订单详情*/
            orderDetailRepository.deleteById(orderDetail.getDetailId());
        }
        /*删除主订单*/
        orderMasterRepository.delete(orderMaster);
        return null;
    }

    @Override
    public List<OrderDTO> findByOpenId(String openId) {

        log.info("findByOpenId={}", openId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<OrderMaster> orderMasters = orderMasterRepository.findByOpenId(openId);
        log.info("orderMasters={}", orderMasters);
        if(orderMasters.size() == 0){
            throw new SellsException(ResultEnum.ORDER_NOT_EXIST);
        }
        for(OrderMaster orderMaster : orderMasters){
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            log.info("订单赋值： ", orderDTO.toString());
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
            if(orderDetails.size() == 0){
                throw new SellsException(ResultEnum.ORDER_DETAIL_EMPTY);
            }
            orderDTO.setOrderDetailList(orderDetails);
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    @Override
    public OrderMaster paid(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if(orderMaster == null){
            throw new SellsException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            throw new SellsException(ResultEnum.ORDER_ALREADY_PAY);
        }
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());

        return orderMasterRepository.save(orderMaster);
    }


    @Override
    public List<OrderDTO> findAll() {

        List<OrderDTO> orderDTOS = new ArrayList<>();

        List<String> openIds = new ArrayList<>();
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        for(OrderMaster orderMaster : orderMasterList){
            if(!openIds.contains(orderMaster.getOpenId())){
                openIds.add(orderMaster.getOpenId());
            }
        }
        for(String openId : openIds){
            List<OrderDTO> ODL = findByOpenId(openId);
            orderDTOS.addAll(ODL);
        }
       return orderDTOS;
    }

    @Override
    public OrderDTO findOne(String openId, String orderId) {
        OrderDTO orderDTO = new OrderDTO();
        OrderMaster orderMaster = orderMasterRepository.findByOpenIdAndOrderId(openId, orderId);
        if(orderMaster == null){
            throw new SellsException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);

        return orderDTO;
    }
}
