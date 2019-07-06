package com.xc.sell.dto;

import com.xc.sell.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class CarDTO {

    private Integer carId;

    private String productId;

    private Integer productQuantity;

    private String openId;

    private ProductInfo productInfo;
}
