package com.xc.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class CarInfo {
    @Id
    @GeneratedValue
    private Integer carId;

    private String productId;

    private Integer productQuantity;

    private String openId;
}
