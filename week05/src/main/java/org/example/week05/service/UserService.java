package org.example.week05.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.week05.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface UserService extends IService<User> {
    IPage<User> pageList(int page, int size);
}