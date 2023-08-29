package com.example.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CaffeineTest {
    @Test
    public void testCache() {

        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .maximumSize(10_000)
                .build();

        // 查找一个缓存元素， 没有查找到的时候返回null
        String key = "key";

        String value = cache.getIfPresent(key);
        System.out.println(value);
        // null

        // 查找缓存，如果缓存不存在则生成缓存元素, 如果无法生成则返回null
        value = cache.get(key, k -> "value");
        System.out.println(value);
        // value

        // 移除一个缓存元素
        cache.invalidate(key);
        value = cache.getIfPresent(key);
        System.out.println(value);
        // null


        // 添加或者更新一个缓存元素
        cache.put(key, "newValue");
        value = cache.getIfPresent(key);
        System.out.println(value);
        // newValue


        // 延迟4秒
        try {
            Thread.sleep(4 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再次获取缓存为null
        value = cache.getIfPresent(key);
        System.out.println(value);
        // null
    }

}
