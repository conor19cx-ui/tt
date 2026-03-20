package org.example.week03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author mqxu
 * @date 2026/3/20
 * @description AppConfig
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String appName;
    private String version;
    private String description;
    private Boolean published;
    private Author author;
    private List<String> features;

    /**
     * 静态内部类
     */
    @Data
    private static class Author {
        private String name;
        private String website;
        private String email;
    }
}