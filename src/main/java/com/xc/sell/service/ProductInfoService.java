package com.xc.sell.service;

import com.xc.sell.dataobject.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    //小程序端API

    /*通过Id获取一个商品*/
    ProductInfo findOne(String productId);

    /*获取全部上架商品*/
    List<ProductInfo> findOnseleAll();

    /*通过category_id获取商品详情*/

    List<ProductInfo> findByCategoryId(Integer categoryId);

    /*通过category_id和product_status获取商品详情*/

    List<ProductInfo> findByCtegoryIdAndProductStatus(Integer categoryId, Integer status);




    //admin
    /*添加商品*/
    ProductInfo save(ProductInfo productInfo);

    /*获取全部商品列表*/
    List<ProductInfo> findAll();

    /*加库存*/
    void increaseStock(String productId, Integer increaseNum);

    /*减库存*/
    void decreaseStock(String productId, Integer decreaseNum);

    /*上架商品*/
    ProductInfo onSale(String productId);

    /*下架商品*/
    ProductInfo offSale(String productId);
}
