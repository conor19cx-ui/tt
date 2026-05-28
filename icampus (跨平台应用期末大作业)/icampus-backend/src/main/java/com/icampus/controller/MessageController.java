package com.icampus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icampus.model.Message;
import com.icampus.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    public Map<String, Object> getMessages(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId,
            @RequestParam(required = false) String type) {
        Map<String, Object> result = new HashMap<>();
        Page<Message> pageResult = messageService.getMessages(page, size, userId, type);
        result.put("code", 200);
        result.put("data", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        result.put("pages", pageResult.getPages());
        return result;
    }

    @GetMapping("/unreadCount")
    public Map<String, Object> getUnreadCount(@RequestParam Long userId) {
        Map<String, Object> result = new HashMap<>();
        long count = messageService.getUnreadCount(userId);
        result.put("code", 200);
        result.put("data", count);
        return result;
    }

    @PostMapping("/markRead")
    public Map<String, Object> markAsRead(@RequestBody Map<String, Long> params) {
        Map<String, Object> result = new HashMap<>();
        Long id = params.get("id");
        boolean success = messageService.markAsRead(id);
        if (success) {
            result.put("code", 200);
            result.put("message", "标记成功");
        } else {
            result.put("code", 500);
            result.put("message", "标记失败");
        }
        return result;
    }
}
