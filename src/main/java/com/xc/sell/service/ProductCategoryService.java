package com.xc.sell.service;

import com.xc.sell.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findByCategoryId(Integer categoryId);

    List<ProductCategory> findAll();

    ProductCategory findByCategoryName(String categoryName);

    ProductCategory save(ProductCategory productCategory);

}
