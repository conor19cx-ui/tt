package com.icampus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icampus.mapper.MessageMapper;
import com.icampus.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {

    public Page<Message> getMessages(Integer page, Integer size, Long userId, String type) {
        Page<Message> pageParam = new Page<>(page, size);
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (type != null && !type.equals("") && !type.equals("全部")) {
            wrapper.eq("type", type);
        }
        wrapper.orderByDesc("create_time");
        return this.page(pageParam, wrapper);
    }

    public long getUnreadCount(Long userId) {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("is_read", 0);
        return this.count(wrapper);
    }

    public boolean markAsRead(Long id) {
        Message message = new Message();
        message.setId(id);
        message.setIsRead(1);
        return this.updateById(message);
    }

    public boolean createMessage(Long userId, String title, String content) {
        Message message = new Message();
        message.setUserId(userId);
        message.setTitle(title);
        message.setContent(content);
        message.setType("系统通知");
        message.setIsRead(0);
        return this.save(message);
    }
}
