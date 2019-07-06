package com.xc.sell.service;

import com.xc.sell.dataobject.OrderMaster;
import com.xc.sell.dto.OrderDTO;
import com.xc.sell.viewObject.OrderVO;

import java.util.List;

public interface OrderMasterService {

    //管理端
    /*创建订单*/
    OrderDTO create(OrderDTO orderDTO);

    //小程序端
    /*支付订单*/
    OrderMaster paid(String orderId);

    //共有
    /*取消订单*/
    OrderDTO cancel(String openId, String orderId);

    /*查询订单列表*/
    List<OrderDTO> findAll();

    /*查询单个订单*/
    OrderDTO findOne(String openId, String orderId);

    /*根据openId查询订单列表*/
    List<OrderDTO> findByOpenId(String openId);

}
