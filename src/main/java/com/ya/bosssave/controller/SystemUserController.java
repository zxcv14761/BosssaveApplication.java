package com.ya.bosssave.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ya.bosssave.pojo.SystemUser;
import com.ya.bosssave.service.ISystemUserService;
import com.ya.bosssave.util.JWTutils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@RestController
@Api(value = "myApi", tags = "用戶API接口")
public class SystemUserController  {

    @Resource
    ISystemUserService iSystemUserService;


    @ApiOperation("測試登入")
    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@ApiParam("SystemUser") SystemUser systemUser, HttpServletResponse res) {

        SystemUser user = null;
        try {
            user = iSystemUserService.queryUser(systemUser.getAccount(),systemUser.getPassword());
        if (user != null){
            user.setPassword("");
            ObjectMapper mapper = new ObjectMapper();
            String userJson = mapper.writeValueAsString(user);

            String token = JWTutils.creatJWT(userJson);
            res.setHeader("Authorization", "Bearer "+token);
            return ResponseEntity.status(HttpStatus.OK).body(userJson);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401 帳號密碼錯誤");
        }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 系統錯誤");
        }
    }


}
