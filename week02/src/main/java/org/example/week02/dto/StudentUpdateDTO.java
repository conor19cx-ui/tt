package org.example.week02.dto;

public class StudentUpdateDTO {
    private String name;
    private String mobile;
    private String avatar;

    public StudentUpdateDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
}