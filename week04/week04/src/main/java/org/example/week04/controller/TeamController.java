package org.example.week04.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.week04.common.Result;
import org.example.week04.entity.Team;
import org.example.week04.exception.BusinessException;

@RestController
@RequestMapping("/api/team")
@Slf4j
public class TeamController {

    @PostMapping("/add")
    public Result<String> addTeam(@Valid @RequestBody Team team, HttpServletRequest request) {

        // 获取请求头 token
        String token = request.getHeader("token");

        // 没有 token
        if (token == null || token.trim().isEmpty()) {
            throw new BusinessException(401, "请先登录，未携带token");
        }

        // token 不正确
        if (!"admin".equals(token)) {
            throw new BusinessException(401, "token无效");
        }

        // 测试异常（演示时打开，成功时注释）
         //int i = 1 / 0;

        log.info("添加团队: {}", team);
        return Result.success("添加成功");
    }
}