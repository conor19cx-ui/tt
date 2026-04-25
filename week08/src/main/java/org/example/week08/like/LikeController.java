package org.example.week08.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 点赞接口
    @PostMapping("/do")
    public ResponseEntity<Map<String, Object>> doLike(
            @RequestParam String userId,
            @RequestParam String postId) {

        Map<String, Object> result = new HashMap<>();
        String key = "like:post:" + postId;
        Long addResult = stringRedisTemplate.opsForSet().add(key, userId);

        if (addResult != null && addResult == 1) {
            result.put("code", 200);
            result.put("message", "点赞成功");
            result.put("success", true);
        } else {
            result.put("code", 400);
            result.put("message", "您已经点过赞啦");
            result.put("success", false);
        }
        return ResponseEntity.ok(result);
    }

    // 获取点赞数接口
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getLikeCount(@RequestParam String postId) {
        Map<String, Object> result = new HashMap<>();
        String key = "like:post:" + postId;
        Long count = stringRedisTemplate.opsForSet().size(key);

        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", count);
        result.put("success", true);
        return ResponseEntity.ok(result);
    }
}