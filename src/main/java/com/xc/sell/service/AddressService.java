package com.xc.sell.service;

import com.xc.sell.dataobject.UserAddress;

import java.util.List;

public interface AddressService {

    /*添加地址*/
    UserAddress add(UserAddress userAddress);
    /*删除地址*/
    UserAddress delete(Integer addressId);
    /*修改地址*/
    UserAddress update(UserAddress userAddress);
    /*查找地址*/
    UserAddress findById(Integer addressId);
    /*通过openid查找地址*/
    List<UserAddress> findByOpenId(String openId);
}
