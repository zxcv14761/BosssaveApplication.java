package com.ya.bosssave.mapper;

import com.ya.bosssave.pojo.SystemUser;

import java.util.List;

public interface SystemUserMapper {


     SystemUser queryUser(String account,String password);
     SystemUser queryUserByIdAccount(int id,String account);
}
