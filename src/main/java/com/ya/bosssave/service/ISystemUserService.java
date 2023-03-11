package com.ya.bosssave.service;

import com.ya.bosssave.pojo.SystemUser;

import java.util.List;

public interface ISystemUserService {


    SystemUser queryUser(String account,String password);
    SystemUser queryUserByIdAccount(int id,String account);




}
