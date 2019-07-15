package com.xc.sell.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void set() {

        String key = "xxx";
        String value = "hhh";

//        boolean result = redisUtil.setnx("1", "xc");
//        redisUtil.expire("1", 60);


        System.out.println(redisUtil.getExpire("1"));




    }
}