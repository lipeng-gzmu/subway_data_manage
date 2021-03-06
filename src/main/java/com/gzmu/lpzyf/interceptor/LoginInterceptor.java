package com.gzmu.lpzyf.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object obj = request.getSession().getAttribute("username");
        if (obj==null||obj.toString().equals("")){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return true;
    }
}
