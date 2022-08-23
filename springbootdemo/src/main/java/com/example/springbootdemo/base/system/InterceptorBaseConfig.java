package com.example.springbootdemo.base.system;

import com.google.common.collect.Lists;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * 拦截器配置类
 *
 * @author yuanman
 */
@Configuration
public class InterceptorBaseConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //如果BaseInterceptor需要依赖spring的bean，则BaseInterceptor应该也要交spring管理，这里就应该使用spring注入
        // InterceptorRegistration registration = registry.addInterceptor(baseInterceptor);
        InterceptorRegistration registration = registry.addInterceptor(new BaseInterceptor());
        //拦截所有路径
        registration.addPathPatterns("/**");
        //忽略拦截的路径
        registration.excludePathPatterns("/test1",
                "/test2");
        WebMvcConfigurer.super.addInterceptors(registry);
    }


}
