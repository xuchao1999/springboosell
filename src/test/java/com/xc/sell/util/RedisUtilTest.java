package com.xc.sell.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    /*@Autowired
    private RedisUtil redisUtil;*/

    @Test
    public void set() {

        String key = "xxx";
        String value = "hhh";

        RedisUtil redisUtil =new RedisUtil();
        boolean result = redisUtil.set("1", "xc");
        System.out.println(result);
    }
}