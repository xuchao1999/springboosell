package com.xc.sell.service.impl;

import com.xc.sell.dataobject.ProductInfo;
import com.xc.sell.exception.SellsException;
import com.xc.sell.repository.ProductInfoRepository;
import com.xc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(String productId, Integer increaseNum) {
        ProductInfo productInfo = findOne(productId);
        if(productInfo == null){
            throw new SellsException(200, "商品不存在");
        }
        productInfo.setProductStock(productInfo.getProductStock() + increaseNum);
        save(productInfo);
    }

    @Override
    public void decreaseStock(String productId, Integer decreaseNum){
        ProductInfo productInfo = findOne(productId);
        if(productInfo == null){
            throw new SellsException(200, "商品不存在");
        }
        if(productInfo.getProductStock() - decreaseNum < 0){
            throw new SellsException(101, "库存不足,不能减库存");
        }
        productInfo.setProductStock(productInfo.getProductStock() - decreaseNum);
        save(productInfo);
    }

    @Override
    public List<ProductInfo> findOnseleAll() {

        return repository.findByProductStatus(Integer.valueOf(0));
    }

    @Override
    public List<ProductInfo> findByCategoryId(Integer categoryId) {
        List<ProductInfo> productInfos = repository.findByCategoryId(categoryId);
        return productInfos;
    }

    @Override
    public List<ProductInfo> findByCtegoryIdAndProductStatus(Integer categoryId, Integer status) {
        return repository.findByCategoryIdAndProductStatus(categoryId, status);
    }

    @Override
    public ProductInfo onSale(String productId){

        ProductInfo productInfo = findOne(productId);

        if(productInfo == null){
            throw new SellsException(200, "商品不存在，不能上架");
        }
        if(productInfo.getProductStock() == 0){
            throw new SellsException(100, "库车为0，不能上架");
        }
        productInfo.setProductStatus(0);
        return save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {

        ProductInfo productInfo = findOne(productId);
        if(productInfo == null){
            throw new SellsException(200, "商品不存在，不能下架");
        }
        productInfo.setProductStatus(1);
        return save(productInfo);
    }
}
