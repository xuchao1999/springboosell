package com.xc.sell.viewObject;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailVO {

    private String productName;

    private String productIcon;

    private BigDecimal productPrice;

    private Integer productQuantity;

}
