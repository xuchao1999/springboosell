package com.xc.sell.service.impl;

import com.xc.sell.dataobject.UserInfo;
import com.xc.sell.repository.UserInfoRepository;
import com.xc.sell.repository.UserRepository;
import com.xc.sell.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserInfo crate(UserInfo userInfo) {
        return repository.save(userInfo);
    }

    @Override
    public UserInfo findByOpenId(String openId) {
        return repository.findByOpenId(openId);
    }

    @Override
    public List<UserInfo> findAll() {
        return repository.findAll();
    }
}
