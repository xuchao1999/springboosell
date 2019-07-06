package com.xc.sell.repository;

import com.xc.sell.dataobject.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    private UserRepository repository;
    @Test
    public void findByOpenId(){
        UserInfo userInfo = repository.findByOpenId("15565408894931775170");
        Assert.assertNotNull(userInfo);
    }
}