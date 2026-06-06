package com.icampus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload-dir:../static/uploads}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /static/uploads/** 映射到实际的上传目录
        String absolutePath = new File(uploadDir).getAbsolutePath() + File.separator;
        registry.addResourceHandler("/static/uploads/**")
                .addResourceLocations("file:" + absolutePath);
    }
}
