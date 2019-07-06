package com.xc.sell.viewObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xc.sell.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("categoryId")
    private Integer categoryId;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

    public ProductVO(String categoryName, Integer categoryId, List<ProductInfoVO> productInfoVOList) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.productInfoVOList = productInfoVOList;
    }
}
