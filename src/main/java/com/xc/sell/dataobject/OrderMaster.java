package com.xc.sell.dataobject;

import com.xc.sell.enums.OrderStatusEnum;
import com.xc.sell.enums.PayStatusEnum;
import com.xc.sell.util.DateUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class OrderMaster {
    @Id
    private String orderId;

    private String openId;

    private BigDecimal orderAmount;

    private Integer addressId;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime = DateUtil.getCurrentDate();

    private Date updateTime = DateUtil.getCurrentDate();
}
