package com.example.demo.utils;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * 参考 https://blog.csdn.net/qq_49067108/article/details/119826547
 */
@Service
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    // key前缀，区分不同应用
    public static final String Prefix = "spring-boot-redis";

    /**
     * 获取key
     *
     * @param key
     * @return
     */
    private String getKey(String key) {
        return Prefix + ":" + key;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public void set(final String key, Object value) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(this.getKey(key), JSON.toJSONString(value));
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public void set(final String key, Object value, Long expireTime) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(this.getKey(key), JSON.toJSONString(value), expireTime, TimeUnit.SECONDS);
    }

    /**
     * 批量删除对应的value
     *
     * @param key
     */
    public void delete(final String key) {
        redisTemplate.delete(this.getKey(key));
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean hasKey(final String key) {
        return redisTemplate.hasKey(this.getKey(key));
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public <T> T get(final String key, Class<T> clazz) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String value = operations.get(this.getKey(key));

        return JSON.parseObject(value, clazz);
    }
}