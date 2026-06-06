package com.icampus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icampus.model.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {
}
