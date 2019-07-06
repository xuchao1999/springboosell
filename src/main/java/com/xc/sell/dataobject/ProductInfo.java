package com.xc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class ProductInfo {
    @Id
    private String productId;
    private Integer categoryId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;
    private Date createTime;
    private Date updateTime;
}
