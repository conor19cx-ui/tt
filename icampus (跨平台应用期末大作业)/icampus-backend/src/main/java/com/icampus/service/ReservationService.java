package com.icampus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icampus.mapper.ReservationMapper;
import com.icampus.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService extends ServiceImpl<ReservationMapper, Reservation> {

    @Autowired
    private MessageService messageService;

    public Page<Reservation> getReservations(Integer page, Integer size, String status) {
        Page<Reservation> pageParam = new Page<>(page, size);
        QueryWrapper<Reservation> wrapper = new QueryWrapper<>();
        if (status != null && !status.equals("") && !status.equals("全部")) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("create_time");
        return this.page(pageParam, wrapper);
    }

    public Page<Reservation> getMyReservations(Integer page, Integer size, Long userId, String status) {
        Page<Reservation> pageParam = new Page<>(page, size);
        QueryWrapper<Reservation> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (status != null && !status.equals("") && !status.equals("全部")) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("create_time");
        return this.page(pageParam, wrapper);
    }

    public Reservation getReservationById(Long id) {
        return this.getById(id);
    }

    public boolean createReservation(Reservation reservation) {
        reservation.setStatus("待处理");
        boolean saved = this.save(reservation);
        if (saved) {
            String title = "";
            String content = "";
            String type = reservation.getServiceType();
            if ("selfroom".equals(type)) {
                title = "自习室预约成功";
                content = "您的自习室预约已提交成功，请准时前往";
            } else if ("repair".equals(type)) {
                title = "宿舍报修提交成功";
                content = "您的宿舍报修申请已提交，维修人员将尽快处理";
            } else if ("errands".equals(type)) {
                title = "跑腿代取订单已提交";
                content = "您的跑腿代取订单已提交，骑手将尽快接单";
            }
            if (!title.isEmpty()) {
                messageService.createMessage(reservation.getUserId(), title, content);
            }
        }
        return saved;
    }

    public boolean cancelReservation(Long id) {
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setStatus("已取消");
        return this.updateById(reservation);
    }
}
