package org.example.week02.dto;

import org.example.week02.constant.GenderEnum;
import java.time.LocalDate;

public class StudentAddDTO {
        private String name;
        private String mobile;
        private GenderEnum gender;
        private String avatar;
        private LocalDate birthday;

        public StudentAddDTO() {}

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }

        public GenderEnum getGender() { return gender; }
        public void setGender(GenderEnum gender) { this.gender = gender; }

        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }

        public LocalDate getBirthday() { return birthday; }
        public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
}