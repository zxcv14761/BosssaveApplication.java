package com.ya.bosssave.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ya.bosssave.pojo.SystemUser;
import com.ya.bosssave.service.ISystemUserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class JwtTokenValidationAspect {

    @Resource
    ISystemUserService iSystemUserService;

    @Pointcut("@annotation(com.ya.bosssave.util.jwtTokenValidation)")
    public void jwtTokenValidation() {
    }

    @Around("jwtTokenValidation()")
    public Object validateJwtToken(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 權限不足");
        }
        bearerToken =bearerToken.substring(6);
        System.out.println(bearerToken);
        ObjectMapper mapper = new ObjectMapper();
        String subject;

        SystemUser user;
        try {
            subject = JWTutils.parseJWT(bearerToken).getSubject();
            user = mapper.readValue(subject, SystemUser.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 權限不足");
        }

        String account = user.getAccount();
        int userid = user.getId();
        SystemUser User = iSystemUserService.queryUserByIdAccount(userid, account);

        if (User == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 權限不足");
        }

        Object result = joinPoint.proceed();
        return result;
    }
}

