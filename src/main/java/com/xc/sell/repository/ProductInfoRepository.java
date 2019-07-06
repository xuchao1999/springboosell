package com.xc.sell.repository;

import com.xc.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    List<ProductInfo> findByCategoryId(Integer categoryId);

    List<ProductInfo> findByCategoryIdAndProductStatus(Integer categoryId, Integer productStatus);
}
