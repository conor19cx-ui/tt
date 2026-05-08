package org.example.week10.config;

import lombok.RequiredArgsConstructor;
import org.example.week10.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // 自动注入LogInterceptor
public class InterceptorConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/api/test") // 只拦截你的接口
                .order(1); // 拦截器顺序，越小越先执行
    }
}