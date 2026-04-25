package org.example.week08;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class RedisTemplateTest {

    // 注入通用 RedisTemplate
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisTemplate() {
        // 1. 测试字符串操作，设置20秒过期
        redisTemplate.opsForValue().set("code:13900003333", "1234", 20, TimeUnit.SECONDS);
        String code = Objects.requireNonNull(redisTemplate.opsForValue().get("code:13900003333")).toString();
        log.info("13900003333 验证码测试结果: {}", code);

        // 2. 测试对象操作
        Address address = new Address();
        address.setCity("南京市");
        address.setStreet("栖霞区羊山北路1号");
        address.setZipCode("210000");

        User user = new User();
        user.setName("张三");
        user.setAge(22);
        user.setEmail("zhangsan@qq.com");
        user.setAddress(address);

        // 3. 将对象存入 Redis
        redisTemplate.opsForValue().set("user:001", user);

        // 4. 取出对象（无需强转，RedisTemplate 已通过配置类支持序列化）
        Object userObj = redisTemplate.opsForValue().get("user:001");
        User user2 = (User) userObj;

        log.info("user:001 测试结果: {}", user2);
    }
}