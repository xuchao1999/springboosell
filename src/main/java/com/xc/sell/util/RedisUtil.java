package com.xc.sell.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value){
        try{
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 存入缓存，带失效时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time){
        try{
            if(time > 0){
                redisTemplate.opsForValue().set(key, value, time);
            }else{
                set(key, value);
            }

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 如果key不存在则设置key-value,如果存在则不设置
     * @param key
     * @param value
     * @return
     */
    public boolean setnx(String key, Object value){
        try{
            redisTemplate.opsForValue().setIfAbsent(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置key的值并返回key的旧值
     * @param key
     * @param value
     * @return
     */
    public Object getset(String key, Object value){

        return redisTemplate.opsForValue().getAndSet(key, value);

    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try{

            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Object get(String key){
        try {
            return key == null ? null : redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 指定缓存失效时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time){
        try{
            if(time > 0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取指定缓存的失效时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return key == null ? null : redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     * @param keys
     */
    public void del(String... keys){
        if(keys != null && keys.length > 0){
            if(keys.length == 1){
                redisTemplate.delete(keys[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    /**
     * 递增\递减
     * @param key
     * @param delta
     * @param type
     * @return
     */
    public long incrementOrDecrement(String key, long delta, String type){
        try{
            if(delta < 0){
                throw new RuntimeException("delta必须大于零");
            }
            if(type.equals("increment")){
                return redisTemplate.opsForValue().increment(key, delta);
            }else if(type.equals("decrement")){
                return redisTemplate.opsForValue().increment(key, -delta);
            }else{
                throw new RuntimeException("请输入正确的type值");
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * HashGet
     * @param key not null
     * @param item not null
     * @return
     */
    public Object hget(String key, Object item){
        if(key != null && item != null){
            return redisTemplate.opsForHash().get(key, item);
        }else{
            return null;
        }
    }

    /**
     * 获取hashkey对应的所有键值对
     * @param key
     * @return
     */
    public Map<Object, Object> hmget(String key){
        return key == null ? null : redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key
     * @param map
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map){
        try{
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * hashset 设置失效时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map, long time){
        try{
            hmset(key, map);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向hash表中添加数据，如果表不存在则创建表
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hset(String key, String item, Object value){
        try{
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向hash表中添加数据，如果表不存在则创建表，
     * 并设置失效时间，如果已存在失效时间，则替换原有时间
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
    public boolean hset(String key, String item, Object value, long time){
        try{
            hset(key, item, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中数据
     * @param key
     * @param items
     */
    public void hdel(String key, Object... items){

        redisTemplate.opsForHash().delete(key, items);
    }

    /**
     * 判断hash表中是否有该值
     * @param key
     * @param item
     * @return
     */
    public boolean hHasKey(String key, Object item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增\递减
     * 递增时如果不存在则或创建一个并返回增加后的值
     * @param key
     * @param item
     * @param dalte
     * @param type
     * @return
     */
    public double hIncrementorHDecrement(String key, String item, double dalte, String type){
        if(type == "increment"){
            return redisTemplate.opsForHash().increment(key, item, dalte);
        }else if(type == "decrement"){
            return redisTemplate.opsForHash().increment(key, item, -dalte);
        }else{
            throw new RuntimeException("请输入正确的type值");
        }
    }

}
