package com.example.springbootdemo.base.system;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
/**
 * 公共过滤器
 * @author yuanman
 */
@Slf4j
public class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("BaseFilter初始化");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("BaseFilter执行开始执行");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("BaseFilter执行完毕");
    }

    @Override
    public void destroy() {
        log.info("BaseFilter销毁");
        Filter.super.destroy();
    }
}
