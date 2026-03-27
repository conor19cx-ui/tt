package org.example.week04.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 核心：把 /upload/** 请求，映射到 D盘的week04_upload文件夹
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                // 必须加 file: 前缀，指向本地磁盘目录
                .addResourceLocations("file:D:/week04_upload/");
    }
}