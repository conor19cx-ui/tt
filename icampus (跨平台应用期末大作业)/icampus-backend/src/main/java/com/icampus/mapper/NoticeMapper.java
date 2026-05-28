package com.icampus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icampus.model.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
