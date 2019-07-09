package com.xc.sell.service.impl;

import com.xc.sell.service.RedisService;
import com.xc.sell.util.RedisUtil;
import jdk.internal.vm.compiler.collections.EconomicMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean lock(String key, String value) {

        if(redisUtil.setnx(key, value)){
            return true;
        }

        String currentValue = redisUtil.get(key).toString();
        if(!currentValue.isEmpty() && Long.parseLong(currentValue) < System.currentTimeMillis()){
            String oldValue = redisUtil.getset(key, value).toString();
            if(!oldValue.isEmpty() && oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean unlock(String key, String value) {

        try{
            String currentValue = redisUtil.get(key).toString();
            if(!currentValue.isEmpty() && currentValue.equals(value)){
                redisUtil.del(key);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
