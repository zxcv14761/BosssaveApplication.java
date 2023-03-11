package com.ya.bosssave.service.serviceImpl;

import com.ya.bosssave.mapper.SystemUserMapper;
import com.ya.bosssave.pojo.SystemUser;
import com.ya.bosssave.service.ISystemUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ISystemUserServiceImpl implements ISystemUserService {
    @Resource
    SystemUserMapper systemUserMapper;



    @Override
    public SystemUser queryUser(String account,String password) {

        SystemUser systemUser = systemUserMapper.queryUser(account,password);
            return systemUser;


    }

    @Override
    public SystemUser queryUserByIdAccount(int id, String account) {

        SystemUser user = systemUserMapper.queryUserByIdAccount(id, account);
        return user;
    }
}
