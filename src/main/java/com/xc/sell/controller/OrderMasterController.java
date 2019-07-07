package com.xc.sell.controller;

import com.xc.sell.converter.OrderDTO2OrderVOConverter;
import com.xc.sell.converter.OrderForm2OrderDTOConverter;
import com.xc.sell.dataobject.OrderMaster;
import com.xc.sell.dataobject.UserInfo;
import com.xc.sell.dto.OrderDTO;
import com.xc.sell.enums.ResultEnum;
import com.xc.sell.exception.SellsException;
import com.xc.sell.form.OrderForm;
import com.xc.sell.repository.UserInfoRepository;
import com.xc.sell.service.OrderMasterService;
import com.xc.sell.service.UserInfoService;
import com.xc.sell.util.ResultVOUtil;
import com.xc.sell.viewObject.OrderVO;
import com.xc.sell.viewObject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class OrderMasterController {

    @Autowired
    private OrderMasterService service;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OrderDTO2OrderVOConverter orderDTO2OrderVOConverter;

    @PostMapping("submit")
    public ResultVO submit(@RequestBody OrderForm orderForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellsException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(orderDTO.getOrderDetailList().size() == 0){
            log.error("【创建订单】购物车不能为空");
            throw new SellsException(ResultEnum.CART_EMPTY);
        }
        OrderDTO ceateResult = service.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", ceateResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    @GetMapping("list")
    public ResultVO list(@RequestParam("openId") String openId){
        log.error("openId = {}", openId);
        if(StringUtils.isEmpty(openId)){
            log.error("【查询订单列表】openid为空");
            throw new SellsException(ResultEnum.PARAM_ERROR);
        }

        List<OrderVO> orderVOS = new ArrayList<>();
        List<OrderDTO> orderDTOS = service.findByOpenId(openId);

        for(OrderDTO orderDTO : orderDTOS){
            OrderVO orderVO = orderDTO2OrderVOConverter.convert(orderDTO);
            orderVOS.add(orderVO);
        }

        return ResultVOUtil.success(orderVOS);
    }

    @GetMapping("detail")
    public ResultVO detail(@RequestParam("openId") String openId, @RequestParam("orderId") String orderId){
        OrderDTO orderDTO = service.findOne(openId, orderId);
        OrderVO orderVO = orderDTO2OrderVOConverter.convert(orderDTO);
        return ResultVOUtil.success(orderVO);
    }

    @PostMapping("cancel")
    public ResultVO cancel(@RequestParam("openId") String openId, @RequestParam("orderId") String orderId){
        service.cancel(openId, orderId);
        return ResultVOUtil.success();
    }


}

