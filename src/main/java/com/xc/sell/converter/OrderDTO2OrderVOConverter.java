package com.xc.sell.converter;

import com.xc.sell.dataobject.OrderDetail;
import com.xc.sell.dataobject.ProductInfo;
import com.xc.sell.dataobject.UserAddress;
import com.xc.sell.dataobject.UserInfo;
import com.xc.sell.dto.OrderDTO;
import com.xc.sell.enums.ResultEnum;
import com.xc.sell.exception.SellsException;
import com.xc.sell.service.ProductInfoService;
import com.xc.sell.service.impl.AddressServiceImpl;
import com.xc.sell.service.impl.UserInfoServiceImpl;
import com.xc.sell.viewObject.OrderDetailVO;
import com.xc.sell.viewObject.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class OrderDTO2OrderVOConverter {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private AddressServiceImpl addressService;

    public OrderDTO2OrderVOConverter (){}


    public OrderVO convert(OrderDTO orderDTO){

        log.error("orderDTO={}", orderDTO);
        UserInfo userInfo = userInfoService.findByOpenId(orderDTO.getOpenId());
        log.error("userInfo={}", userInfo);
        if(userInfo == null){
            throw new SellsException(ResultEnum.USER_NOT_EXIST);
        }

        UserAddress userAddress = addressService.findById(orderDTO.getAddressId());
        if(userAddress == null){
            throw new SellsException(ResultEnum.ADDRESS_NOT_EXIST);
        }
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderDTO, orderVO);
        orderVO.setPhone(userInfo.getPhone());
        orderVO.setUserName(userInfo.getUsername());
        orderVO.setAddressDetail(userAddress.getAddressDetail());

        List<OrderDetailVO> orderDetailVOS = new ArrayList<>();
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){

            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());

            OrderDetailVO orderDetailVO = new OrderDetailVO();
            orderDetailVO.setProductIcon(productInfo.getProductIcon());
            orderDetailVO.setProductName(productInfo.getProductName());
            orderDetailVO.setProductPrice(productInfo.getProductPrice());
            orderDetailVO.setProductQuantity(orderDetail.getProductQuantity());

            orderDetailVOS.add(orderDetailVO);
        }

        orderVO.setOrderDetailVOS(orderDetailVOS);

        return orderVO;
    }
}
