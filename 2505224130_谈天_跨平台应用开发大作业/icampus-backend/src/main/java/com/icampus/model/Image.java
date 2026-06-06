package com.icampus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("images")
public class Image {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String url;
    private String type;
    private Long relatedId;
    private Long userId;
    private LocalDateTime createTime;
}
