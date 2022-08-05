package com.example.springbootdemo.base.system;

import com.google.common.collect.Lists;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 过滤器配置类
 *
 * @author yuanman
 */
@Configuration
public class FilterBaseConfig {

    @Bean
    public FilterRegistrationBean<Filter> BaseFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        //添加过滤器
        filterFilterRegistrationBean.setFilter(new BaseFilter());
        //设置过滤路径 /* 所有路径
        filterFilterRegistrationBean.setUrlPatterns(Lists.newArrayList("/*"));
        filterFilterRegistrationBean.addUrlPatterns("/*");
        //设置优先级
        filterFilterRegistrationBean.setOrder(0);
        return filterFilterRegistrationBean;
    }
}
