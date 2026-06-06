package com.icampus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icampus.model.Reservation;
import com.icampus.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/list")
    public Map<String, Object> getReservations(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String status) {
        Map<String, Object> result = new HashMap<>();
        Page<Reservation> pageResult = reservationService.getReservations(page, size, status);
        result.put("code", 200);
        result.put("data", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        result.put("pages", pageResult.getPages());
        return result;
    }

    @GetMapping("/my")
    public Map<String, Object> getMyReservations(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId,
            @RequestParam(required = false) String status) {
        Map<String, Object> result = new HashMap<>();
        Page<Reservation> pageResult = reservationService.getMyReservations(page, size, userId, status);
        result.put("code", 200);
        result.put("data", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        result.put("pages", pageResult.getPages());
        return result;
    }

    @GetMapping("/detail")
    public Map<String, Object> getReservationDetail(@RequestParam Long id) {
        Map<String, Object> result = new HashMap<>();
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            result.put("code", 200);
            result.put("data", reservation);
        } else {
            result.put("code", 404);
            result.put("message", "预约不存在");
        }
        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> createReservation(@RequestBody Reservation reservation) {
        Map<String, Object> result = new HashMap<>();
        boolean success = reservationService.createReservation(reservation);
        if (success) {
            result.put("code", 200);
            result.put("message", "提交成功");
        } else {
            result.put("code", 500);
            result.put("message", "提交失败");
        }
        return result;
    }

    @PostMapping("/cancel")
    public Map<String, Object> cancelReservation(@RequestBody Map<String, Long> params) {
        Map<String, Object> result = new HashMap<>();
        Long id = params.get("id");
        boolean success = reservationService.cancelReservation(id);
        if (success) {
            result.put("code", 200);
            result.put("message", "取消成功");
        } else {
            result.put("code", 500);
            result.put("message", "取消失败");
        }
        return result;
    }
}
