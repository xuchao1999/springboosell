package com.xc.sell.dataobject;

import com.xc.sell.util.DateUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class OrderDetail {
    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private Integer productQuantity;

    private Date createTime = DateUtil.getCurrentDate();

    private Date updateTime = DateUtil.getCurrentDate();
}
