package com.icampus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.icampus.mapper")
public class IcampusApplication {
    public static void main(String[] args) {
        SpringApplication.run(IcampusApplication.class, args);
    }
}
