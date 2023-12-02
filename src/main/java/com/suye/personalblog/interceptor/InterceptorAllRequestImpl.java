package com.suye.personalblog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-03
 * Time: 16:25
 */
//拦截所有的请求
@Component
public class InterceptorAllRequestImpl implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("1");
        if ((request.getSession().getAttribute("isLogin")!=null)
                &&"success".equals(request.getSession().getAttribute("isLogin"))){
            return true;
        }else {
            System.out.println("2");
            response.sendRedirect("/admin/logins");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("2");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("3");

    }
}
