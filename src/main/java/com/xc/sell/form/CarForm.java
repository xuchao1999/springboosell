package com.xc.sell.form;

import lombok.Data;

@Data
public class CarForm {

    private String openId;

    private String productId;

    private Integer productQuantity;

    public CarForm (){}
    public CarForm(String openId, String productId, Integer productQuantity) {
        this.openId = openId;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
