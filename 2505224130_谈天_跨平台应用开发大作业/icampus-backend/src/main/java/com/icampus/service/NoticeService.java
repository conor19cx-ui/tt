package com.icampus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icampus.mapper.NoticeMapper;
import com.icampus.model.Notice;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {

    public Page<Notice> getNotices(Integer page, Integer size, String category, String keyword) {
        Page<Notice> pageParam = new Page<>(page, size);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        if (category != null && !category.equals("") && !category.equals("全部")) {
            wrapper.eq("category", category);
        }
        if (keyword != null && !keyword.equals("")) {
            wrapper.like("title", keyword);
        }
        wrapper.orderByDesc("publish_time");
        return this.page(pageParam, wrapper);
    }

    public Notice getNoticeById(Long id) {
        return this.getById(id);
    }
}
