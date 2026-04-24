package org.example.week08.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

// 关键：必须加@Component，让Spring能扫描到这个工具类
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    // 写入key-value并设置过期时间
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // 读取value
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除key
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}