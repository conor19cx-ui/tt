package org.example.filterintercepter.config;

import lombok.RequiredArgsConstructor;
import org.example.filterintercepter.intercepter.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class IntercepterConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**") // 拦截所有/api开头的接口
                .excludePathPatterns("/api/login") // 放行登录接口
                .excludePathPatterns("/api/hello") // 放行限流测试接口
                .order(2); // 执行顺序，比过滤器晚
    }
}