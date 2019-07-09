package com.xc.sell.service;

public interface RedisService {
    /**
     * 加锁
     * @param key 商品id
     * @param value 当前时间 + 超时时间
     * @return
     */
    boolean lock(String key, String value);

    /**
     * 解锁
     * @param key
     * @param value
     * @return
     */
    boolean unlock(String key, String value);
}
