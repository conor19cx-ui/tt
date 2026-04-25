package org.example.week08;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.week08.util.RedisUtil;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class Week08ApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void testRedisConnection() {
        System.out.println("===== 测试Redis连接 =====");
        // 写入一个测试key
        redisUtil.set("test:connection", "success", 60, TimeUnit.SECONDS);
        // 读取这个key
        Object value = redisUtil.get("test:connection");
        System.out.println("读取到的value：" + value);

        if ("success".equals(value)) {
            System.out.println("✅ Redis连接正常，写入/读取成功！");
        } else {
            System.out.println("❌ Redis连接失败！检查application.yml配置");
        }
    }
}