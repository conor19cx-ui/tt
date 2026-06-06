package com.icampus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("reservations")
public class Reservation {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String serviceType;
    private String serviceName;
    private LocalDateTime reserveTime;
    private String location;
    private String description;
    private String images;
    private String contact;
    private Long userId;
    private String status;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
