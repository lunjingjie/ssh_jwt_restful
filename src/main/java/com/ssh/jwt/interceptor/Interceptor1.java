package com.ssh.jwt.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor1 implements HandlerInterceptor {

    // 在进入controller方法之前执行
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("这是Interceptor1的preHandle方法");
        return true;
    }

    // 执行完controller方法dispatcher之后执行
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("这是Interceptor1的postHandle方法");
    }

    // 页面渲染完成之后，返回给用户之前执行
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("这是Interceptor1的afterCompletion方法");
    }
}
