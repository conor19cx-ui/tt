package com.icampus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icampus.mapper.UserMapper;
import com.icampus.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public User login(String username, String password) {
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username).or().eq("phone", username);
        wrapper.eq("password", md5Password);
        return this.getOne(wrapper);
    }

    public User register(User user) {
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        this.save(user);
        return user;
    }

    public User getUserById(Long id) {
        return this.getById(id);
    }

    public boolean updateUser(User user) {
        return this.updateById(user);
    }
}
