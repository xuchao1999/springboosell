package com.xc.sell.controller;

import com.xc.sell.dataobject.OrderMaster;
import com.xc.sell.dto.OrderDTO;
import com.xc.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WxController {


    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 微信支付接口
     * @param openId
     * @param orderId
     */
    @PostMapping("/wxpay")
    public void wxpay(@RequestParam("openId") String openId,
                      @RequestParam("orderId") String orderId){
//        獲取訂單詳情
        OrderDTO orderDTO = orderMasterService.findOne(openId, orderId);



    }
}
