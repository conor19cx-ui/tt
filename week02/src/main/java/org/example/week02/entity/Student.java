package org.example.week02.entity;

import org.example.week02.constant.GenderEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {
    private Long id;
    private String name;
    private String mobile;
    private GenderEnum gender;
    private String avatar;
    private Boolean enabled;
    private LocalDate birthday;
    private LocalDateTime createTime;

    // 无参构造
    public Student() {}

    // 全参构造
    public Student(Long id, String name, String mobile, GenderEnum gender,
                   String avatar, Boolean enabled, LocalDate birthday, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
        this.avatar = avatar;
        this.enabled = enabled;
        this.birthday = birthday;
        this.createTime = createTime;
    }

    // 所有 getter 和 setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public GenderEnum getGender() { return gender; }
    public void setGender(GenderEnum gender) { this.gender = gender; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}