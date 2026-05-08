package org.example.filterintercepter.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.filterintercepter.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final JwtUtil jwtUtil;

    // 登录接口(放行)
    @GetMapping("/login")
    public String login(String username, String password) {
        // 模拟登录校验
        if ("admin".equals(username) && "123456".equals(password)) {
            String token = jwtUtil.generateToken(username, "admin");
            return "登录成功!管理员Token:" + token;
        }
        if ("user".equals(username) && "123456".equals(password)) {
            String token = jwtUtil.generateToken(username, "user");
            return "登录成功!普通用户Token:" + token;
        }
        return "用户名或密码错误";
    }

    // 普通接口(需要登录)
    @GetMapping("/user/info")
    public String userInfo(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String role = (String) request.getAttribute("role");
        return "当前登录用户:" + username + ",角色:" + role;
    }

    // 管理员接口(需要管理员权限)
    @GetMapping("/admin/list")
    public String adminList() {
        return "管理员数据:用户列表、系统配置";
    }

    // 测试限流接口
    @GetMapping("/test/rate")
    public String testRate() {
        return "接口访问成功";
    }

    // 测试限流接口
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Filter & Intercepter!";
    }
}