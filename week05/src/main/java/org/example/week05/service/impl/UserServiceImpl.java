package org.example.week05.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.week05.entity.User;
import org.example.week05.mapper.UserMapper;
import org.example.week05.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public IPage<User> pageList(int page, int size) {
        Page<User> pageParam = new Page<>(page, size);
        return this.page(pageParam);
    }
}