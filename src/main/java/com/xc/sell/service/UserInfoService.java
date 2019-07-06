package com.xc.sell.service;

import com.xc.sell.dataobject.UserInfo;

import java.util.List;

public interface UserInfoService {

    /*添加用户*/
    UserInfo crate(UserInfo userInfo);
    /*查找用户*/
    UserInfo findByOpenId(String openId);
    /*获取用户列表*/
    List<UserInfo> findAll();
}
