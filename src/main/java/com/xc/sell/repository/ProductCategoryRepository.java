package com.xc.sell.repository;

import com.xc.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    ProductCategory findByCategoryName(String categoryName);
}
