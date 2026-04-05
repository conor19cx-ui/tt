package org.example.week05.controller;

import org.example.week05.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/user")
    public User testUser() {
        User user = new User();
        user.setId(999);
        user.setUsername("测试用户");
        user.setPassword("123456");
        user.setAge(20);
        user.setEmail("test@qq.com");
        return user;
    }

    @GetMapping("/time")
    public String getTime() {
        return "当前时间：" + LocalDateTime.now();
    }
}