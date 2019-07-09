package com.xc.sell.controller;

import com.xc.sell.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisUtil redisUtil;
    @GetMapping("/redistest")
    public boolean redistest(){
        boolean result1 = redisUtil.hasKey("1");
        System.out.println(result1);
        boolean result2 = redisUtil.hasKey("0");
        System.out.println(result2);

        Object value1 = redisUtil.get("1");
        System.out.println(value1.toString());
        Object value2 = redisUtil.get("0");
        System.out.println(value2);


        System.out.println(redisUtil.expire("1", 10000000));
        long time = redisUtil.getExpire("1");
        System.out.println(time);
        return redisUtil.set("1", "xuchao");
    }
}
