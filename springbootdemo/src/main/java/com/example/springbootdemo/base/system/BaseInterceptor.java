package com.example.springbootdemo.base.system;

import com.example.springbootdemo.base.util.ResponseUtil;
import com.example.springbootdemo.base.util.UploadFileDetectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共拦截器
 *
 * <pre>preHandle是请求执行前执行的，
 * postHandler是请求结束执行的，但只有preHandle方法返回true的时候才会执行，
 * afterCompletion是视图渲染完成后才执行，同样需要preHandle返回true
 * </pre>
 *
 * @author yuanman
 */
@Slf4j
public class BaseInterceptor implements HandlerInterceptor {
    /**
     * 该方法返回true才会执行其他两个方法postHandle和afterCompletion
     * 返回false则停止流程，api被拦截
     * 在请求处理之前进行调用该方法（Controller方法调用之前）
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器开始执行拦截");
        log.info("拦截器放行请求");
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = resolver.resolveMultipart(request);
            MultiValueMap<String, MultipartFile> multiValueMap = multipartHttpServletRequest.getMultiFileMap();
            if (!multiValueMap.isEmpty()) {
                List<MultipartFile> multipartFileList = new ArrayList<MultipartFile>();
                for (List<MultipartFile> f : multiValueMap.values()) {
                    multipartFileList.addAll(f);
                }
                if (UploadFileDetectionUtil.checkFileAllowedUploads(multipartFileList)) {
                    return true;
                } else {
                    response.setStatus(403);
                    response.setContentType(request.getContentType());
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    writer.print(ResponseUtil.retErrorInfo("上传文件类型错误！"));
                    response.flushBuffer();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器放行请求结束");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 整个请求结束之后被调用，也就是在DispatchServlet渲染了对应的视图之后执行（主要用于进行资源清理工作）
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("拦截器afterCompletion执行");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
