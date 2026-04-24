package org.example.week08;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testStringRedisTemplate() {
        // 存入带30秒过期的数据
        stringRedisTemplate.opsForValue().set("hello", "world");

        // 存入分组KEY（老师要求）
        stringRedisTemplate.opsForValue().set("code:10086", "1234");
        stringRedisTemplate.opsForValue().set("code:10010", "5678");

        // 读取并打印
        String hello = stringRedisTemplate.opsForValue().get("hello");
        String code1 = stringRedisTemplate.opsForValue().get("code:10086");
        String code2 = stringRedisTemplate.opsForValue().get("code:10010");

        log.info("hello = {}", hello);
        log.info("code:10086 = {}", code1);
        log.info("code:10010 = {}", code2);
    }
}