package org.example.filterintercepter.config;

import org.example.filterintercepter.filter.RateLimitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类:注册自定义过滤器
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilterRegistration() {
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimitFilter());
        // 过滤请求路径
        registrationBean.addUrlPatterns("/api/hello", "/api/test/rate");
        registrationBean.setOrder(1);
        registrationBean.setName("rateLimitFilter");
        return registrationBean;
    }
}