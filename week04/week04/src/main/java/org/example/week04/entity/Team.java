package org.example.week04.entity;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

/**
 * Team实体类，绑定配置文件+参数校验
 */
@Data
@Component
public class Team {

    // 负责人：非空、长度2-10位
    @Value("${team.leader}")
    @NotBlank(message = "负责人姓名不能为空")
    @Length(min = 2, max = 10, message = "负责人姓名长度2-10位")
    private String leader;

    // 年龄：30-60之间
    @Value("${team.age}")
    @Max(value = 60, message = "年龄不能大于60")
    @Min(value = 30, message = "年龄不能小于30")
    private Integer age;

    // 邮箱：格式校验
    @Value("${team.email}")
    @Email(message = "邮箱格式不正确")
    private String email;

    // 手机号：11位数字正则
    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号格式不正确")
    private String phone;

    // 创建时间：必须早于当前时间
    @Value("${team.createTime}")
    @Past(message = "创建时间必须早于当前时间")
    private LocalDate createTime;
}