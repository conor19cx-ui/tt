package org.example.week08.sms;

/**
 * 短信验证码Redis Key规则（固定格式：week08:sms:code:手机号）
 */
public final class SmsRedisKey {

    // 核心前缀，必须和代码里一致！
    public static final String PREFIX = "week08:sms:code:";

    private SmsRedisKey() {}

    // 生成完整key：前缀+手机号
    public static String ofPhone(String phone) {
        return PREFIX + phone;
    }
}