package org.example.week10.config;

import org.example.week10.filter.AuthFilter;
import org.example.week10.filter.CorsFilter;
import org.example.week10.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
/*
@Configuration
public class FilterConfig {


    // 跨域过滤器 → 最高优先级
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CorsFilter());
        bean.addUrlPatterns("/api/test");
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // 最优先
        return bean;
    }

    // 日志过滤器
    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogFilter());
        bean.addUrlPatterns("/api/test");
        bean.setOrder(1);
        return bean;
    }

    // 认证过滤器
    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthFilter());
        bean.addUrlPatterns("/api/test");
        bean.setOrder(2);
        return bean;
    }
}*/