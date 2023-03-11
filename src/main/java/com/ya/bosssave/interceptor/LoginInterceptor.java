package com.ya.bosssave.interceptor;


import com.ya.bosssave.util.JWTutils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.PrintWriter;

import static org.springframework.http.HttpStatus.FORBIDDEN;


/**
 * 自定義攔截器類
 */
public class LoginInterceptor implements HandlerInterceptor {


    /**
     * 檢測header中是否有JWTToken
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String bearerToken = request.getHeader("Authorization");

//        System.out.println(bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String subject = null;
            bearerToken = bearerToken.substring(6);
            try {
                subject = JWTutils.parseJWT(bearerToken).getSubject();
            } catch (Exception e) {
                response.setStatus(FORBIDDEN.value());
                PrintWriter writer = response.getWriter();
                response.setContentType("text/html;charset=utf-8");
                writer.print("權限不足");
                writer.close();
            }
            if (subject != null) {
                return true;
            }
        } else {
            response.setStatus(403);
            PrintWriter writer = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            writer.print("權限不足");
            writer.close();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

