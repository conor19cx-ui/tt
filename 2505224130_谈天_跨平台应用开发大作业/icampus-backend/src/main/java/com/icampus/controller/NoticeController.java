package com.icampus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icampus.model.Notice;
import com.icampus.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/notice")
@CrossOrigin
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public Map<String, Object> getNotices(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword) {
        Map<String, Object> result = new HashMap<>();
        Page<Notice> pageResult = noticeService.getNotices(page, size, category, keyword);
        result.put("code", 200);
        result.put("data", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        result.put("pages", pageResult.getPages());
        return result;
    }

    @GetMapping("/detail")
    public Map<String, Object> getNoticeDetail(@RequestParam Long id) {
        Map<String, Object> result = new HashMap<>();
        Notice notice = noticeService.getNoticeById(id);
        if (notice != null) {
            result.put("code", 200);
            result.put("data", notice);
        } else {
            result.put("code", 404);
            result.put("message", "公告不存在");
        }
        return result;
    }
}
