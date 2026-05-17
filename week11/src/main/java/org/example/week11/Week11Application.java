package org.example.week11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 🔥 关键：开启定时任务功能
@EnableScheduling
@SpringBootApplication
public class Week11Application {
    public static void main(String[] args) {
        SpringApplication.run(Week11Application.class, args);
    }
}