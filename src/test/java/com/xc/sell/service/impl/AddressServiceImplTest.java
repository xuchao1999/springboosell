package com.xc.sell.service.impl;

import com.xc.sell.dataobject.UserAddress;
import com.xc.sell.service.AddressService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceImplTest {

    @Autowired
    private AddressService service;
    @Test
    public void add() {
        UserAddress userAddress = new UserAddress();
        userAddress.setOpenId("15565898263241215956");
        userAddress.setProvince("江西省");
        userAddress.setCity("上饶市");
        userAddress.setCounty("上饶县");
        userAddress.setAddressDetail("煌固村");

        Assert.assertNotNull(service.add(userAddress));
    }

    @Test
    public void delete() {

    }

    @Test
    public void update() {
        UserAddress result = service.findById(8);
        result.setAddressDetail("蒋村");
        service.update(result);
        Assert.assertEquals("蒋村", service.update(result).getAddressDetail());
    }

    @Test
    public void findById() {
        UserAddress userAddress = service.findById(8);
        Assert.assertEquals("江西省", userAddress.getProvince());
    }

    @Test
    public void findByOpenId() {
        List<UserAddress> result = service.findByOpenId("15565898263241215956");
        Assert.assertEquals(1, result.size());
    }
}