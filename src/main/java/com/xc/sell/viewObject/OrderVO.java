package com.xc.sell.viewObject;

import com.xc.sell.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderVO {

    private String orderId;

    private String userName;

    private String phone;

    private String openId;

    private String addressDetail;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetailVO> orderDetailVOS;
}
