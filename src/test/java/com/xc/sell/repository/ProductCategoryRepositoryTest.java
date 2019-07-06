package com.xc.sell.repository;

import com.xc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("人气");

        ProductCategory result = productCategoryRepository.save(productCategory);

        Assert.assertNotNull(result);
    }

    @Test
    public void findOneTest(){
        ProductCategory result = productCategoryRepository.findById(3).orElse(null);
        Assert.assertNotNull(result);

    }
    @Test
    public void findByCategoryNameTest(){
        ProductCategory result = productCategoryRepository.findByCategoryName("人气");
        System.out.println(result.toString());
        Assert.assertNotNull(result);
    }
}