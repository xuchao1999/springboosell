package com.xc.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xc.sell.dataobject.OrderDetail;
import com.xc.sell.dto.OrderDTO;
import com.xc.sell.enums.ResultEnum;
import com.xc.sell.exception.SellsException;
import com.xc.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOpenId(orderForm.getOpenId());
        orderDTO.setAddressId(orderForm.getAddressId());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){
            }.getType());
        }catch (Exception e){
            log.error("【对象转换】错误,string={}", orderForm.getItems());
            throw new SellsException(ResultEnum.PARAM_ERROR);
        }
        log.error("【创建订单】参数不正确，orderDetailList={}", orderDetailList);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
