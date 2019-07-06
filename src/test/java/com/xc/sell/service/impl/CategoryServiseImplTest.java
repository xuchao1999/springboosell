package com.xc.sell.service.impl;

import com.xc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiseImplTest {

    @Autowired
    private CategoryServiseImpl categoryServise;
    @Test
    public void findByCategoryId() {
        ProductCategory result = categoryServise.findByCategoryId(3);
        Assert.assertEquals(new Integer(3), result.getCategoryId());
    }

    @Test
    public void findAll() {

        List<ProductCategory> result = categoryServise.findAll();
        Assert.assertEquals(2, result.size());

    }

    @Test
    public void findByCategoryName() {
        ProductCategory result = categoryServise.findByCategoryName("五一狂欢");
        Assert.assertEquals("五一狂欢", result.getCategoryName());

    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("十一狂欢");
        ProductCategory result = categoryServise.save(productCategory);
        Assert.assertNotNull(result);
    }
}