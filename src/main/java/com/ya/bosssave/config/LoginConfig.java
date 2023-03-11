package com.ya.bosssave.config;


import com.ya.bosssave.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //註冊攔截器添加規則
        registry.addInterceptor(new LoginInterceptor())

                .addPathPatterns("/goods/**")
                //放行請求接口
                .excludePathPatterns("/auth/login")
                .excludePathPatterns("/error");


    }
}
