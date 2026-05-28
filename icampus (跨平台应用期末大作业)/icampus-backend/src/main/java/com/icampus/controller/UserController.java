package com.icampus.controller;

import com.icampus.model.User;
import com.icampus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        String username = params.get("username");
        String password = params.get("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            result.put("code", 400);
            result.put("message", "账号和密码不能为空");
            return result;
        }

        if (password.length() < 6) {
            result.put("code", 400);
            result.put("message", "密码长度至少为6位");
            return result;
        }

        User user = userService.login(username, password);
        if (user != null) {
            result.put("code", 200);
            result.put("message", "登录成功");
            user.setPassword(null);
            result.put("data", user);
        } else {
            result.put("code", 401);
            result.put("message", "账号或密码错误");
        }
        return result;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            result.put("code", 400);
            result.put("message", "账号不能为空");
            return result;
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            result.put("code", 400);
            result.put("message", "密码长度至少为6位");
            return result;
        }

        User newUser = userService.register(user);
        newUser.setPassword(null);
        result.put("code", 200);
        result.put("message", "注册成功");
        result.put("data", newUser);
        return result;
    }

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(@RequestParam Long userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getUserById(userId);
        if (user != null) {
            user.setPassword(null);
            result.put("code", 200);
            result.put("data", user);
        } else {
            result.put("code", 404);
            result.put("message", "用户不存在");
        }
        return result;
    }

    @PostMapping("/update")
    public Map<String, Object> updateUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.updateUser(user);
        if (success) {
            result.put("code", 200);
            result.put("message", "更新成功");
        } else {
            result.put("code", 500);
            result.put("message", "更新失败");
        }
        return result;
    }
}
