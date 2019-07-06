package com.xc.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xc.sell.dataobject.OrderDetail;
import com.xc.sell.enums.OrderStatusEnum;
import com.xc.sell.enums.PayStatusEnum;
import com.xc.sell.util.DateUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String openId;

    private BigDecimal orderAmount;

    private Integer addressId;

    private List<OrderDetail> orderDetailList;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    @JsonIgnore
    private Date createTime = DateUtil.getCurrentDate();

    @JsonIgnore
    private Date updateTime = DateUtil.getCurrentDate();

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", openId='" + openId + '\'' +
                ", orderAmount=" + orderAmount +
                ", addressId=" + addressId +
                ", orderDetailList=" + orderDetailList +
                ", orderStatus=" + orderStatus +
                ", payStatus=" + payStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
