package org.example.week08.sms;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.example.week08.util.RedisUtil;
import org.example.week08.sms.dto.SendCodeResponse;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SmsVerifyCodeService {

    private static final Logger log = LoggerFactory.getLogger(SmsVerifyCodeService.class);
    public static final int DEFAULT_TTL_SECONDS = 5 * 60;
    private final RedisUtil redisUtil;

    public SendCodeResponse sendCode(String phone) {
        // 1. 生成6位验证码
        String code = String.format("%06d", ThreadLocalRandom.current().nextInt(1_000_000));

        // 2. 硬写key格式（跳过可能出错的工具类，兜底）
        String key = "week08:sms:code:" + phone;

        // 3. 强制写入Redis，5分钟过期
        redisUtil.set(key, code, DEFAULT_TTL_SECONDS, TimeUnit.SECONDS);

        // 4. 双打印，确保能看到
        log.info("【短信验证码】手机号：{}，验证码：{}，RedisKey：{}", phone, code, key);
        System.out.println("======= 验证码信息 =======");
        System.out.println("手机号：" + phone);
        System.out.println("验证码：" + code);
        System.out.println("RedisKey：" + key);
        System.out.println("=========================");

        // 5. 返回响应（包含验证码明文，Apifox能直接看到）
        return new SendCodeResponse(phone, DEFAULT_TTL_SECONDS, code);
    }

    public boolean validateCode(String phone, String input) {
        // 同样硬写key格式
        String key = "week08:sms:code:" + phone;
        Object raw = redisUtil.get(key);

        if (raw == null) {
            System.out.println("验证码已过期/不存在，RedisKey：" + key);
            return false;
        }

        String cached = Objects.toString(raw, "");
        if (!cached.equals(input)) {
            System.out.println("验证码错误！输入：" + input + "，实际：" + cached);
            return false;
        }

        // 校验成功，删除key
        redisUtil.delete(key);
        System.out.println("验证码校验成功，已删除RedisKey：" + key);
        return true;
    }
}