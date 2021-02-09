package com.example.appcommon.config;

import com.example.appcommon.interceptor.RestResultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: wcg
 * @Date: 2021/2/9 16:05
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestResultInterceptor()).addPathPatterns("/**");
    }
}
