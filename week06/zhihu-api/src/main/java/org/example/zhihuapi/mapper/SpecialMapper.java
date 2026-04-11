package org.example.zhihuapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.zhihuapi.entity.Special;
import org.example.zhihuapi.service.SpecialService;


@Mapper
public interface SpecialMapper extends BaseMapper<Special> {

}