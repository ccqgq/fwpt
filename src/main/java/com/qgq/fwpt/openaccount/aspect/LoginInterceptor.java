package com.qgq.fwpt.openaccount.aspect;


import com.qgq.fwpt.common.dto.ResultDto;
import com.qgq.fwpt.common.utils.Json;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

public class LoginInterceptor implements HandlerInterceptor {

    private String userNotLoginMsg = Json.object2String(new ResultDto(403,"未登录",""));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object isLogin = session.getAttribute("user");
        if (isLogin != null) {
            return true;
        }
        response.setStatus(200);
        response.setContentType(APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().append(userNotLoginMsg);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}

