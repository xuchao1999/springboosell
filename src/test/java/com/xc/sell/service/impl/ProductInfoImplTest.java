package com.xc.sell.service.impl;

import com.xc.sell.dataobject.ProductInfo;
import com.xc.sell.repository.ProductInfoRepository;
import com.xc.sell.service.ProductInfoService;
import com.xc.sell.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    public void findAll() {
        List<ProductInfo> productInfos = productInfoService.findAll();
        Assert.assertEquals(3, productInfos.size());
    }

    @Test
    public void findOnselaAll(){
        List<ProductInfo> result = productInfoService.findOnseleAll();
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void findByCategoryId(){
        List<ProductInfo> result = productInfoService.findByCategoryId(4);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductStatus(Integer.valueOf(0));
        productInfo.setProductStock(Integer.valueOf(10));
        productInfo.setCategoryId(4);
        productInfo.setCreateTime(DateUtil.getCurrentDate());
        productInfo.setUpdateTime(DateUtil.getCurrentDate());
        productInfo.setProductDescription("这是个好东西");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductId("1228756");
        productInfo.setProductName("肥仔快乐水");
        productInfo.setProductPrice(new BigDecimal(2.5));

        Assert.assertNotNull(productInfoService.save(productInfo));
    }

    @Test
    public void increaseStock() {
        productInfoService.increaseStock("123456", 1);
        Assert.assertEquals(Integer.valueOf(4), productInfoService.findOne("123456").getProductStock());

    }

    @Test
    public void decreaseStock() {
        productInfoService.decreaseStock("1228756", 1);
        Assert.assertEquals(Integer.valueOf(9), productInfoService.findOne("1228756").getProductStock());


    }

    @Test
    public void onSale() {
        productInfoService.onSale("1228756");
        Assert.assertEquals(Integer.valueOf(0), productInfoService.findOne("1228756").getProductStatus());
    }

    @Test
    public void offSale() {
        productInfoService.offSale("1228756");
        Assert.assertEquals(Integer.valueOf(0), productInfoService.findOne("1228756").getProductStatus());
    }
}