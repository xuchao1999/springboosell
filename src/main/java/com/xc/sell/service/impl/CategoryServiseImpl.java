package com.xc.sell.service.impl;

import com.xc.sell.dataobject.ProductCategory;
import com.xc.sell.repository.ProductCategoryRepository;
import com.xc.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiseImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findByCategoryId(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductCategory findByCategoryName(String categoryName) {
        return repository.findByCategoryName(categoryName);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
