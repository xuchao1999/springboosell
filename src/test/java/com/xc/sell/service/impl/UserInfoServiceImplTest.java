package com.xc.sell.service.impl;

import com.xc.sell.dataobject.UserInfo;
import com.xc.sell.service.UserInfoService;
import com.xc.sell.util.KeyUtil;
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
public class UserInfoServiceImplTest {

    @Autowired
    private UserInfoService service;

    @Test
    public void crate() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("1212");
        userInfo.setOpenId(KeyUtil.getUniqueKey());
        userInfo.setUsername("zhangshan");
        userInfo.setPassword("12345667");
        userInfo.setPhone("12345678901");

        Assert.assertNotNull(service.crate(userInfo));
    }

    @Test
    public void findByOpenId() {
        String openId = "15565408894931775170";
        UserInfo userInfo = service.findByOpenId(openId);
        Assert.assertEquals(String.valueOf(0), userInfo.getId());
    }

    @Test
    public void findAll() {
        List<UserInfo> userInfoList = service.findAll();
        Assert.assertEquals(2, userInfoList.size());
    }
}