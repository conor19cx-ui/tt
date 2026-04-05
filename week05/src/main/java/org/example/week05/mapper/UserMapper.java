package org.example.week05.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.week05.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}