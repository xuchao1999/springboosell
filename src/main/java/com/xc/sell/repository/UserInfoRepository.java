package com.xc.sell.repository;

import com.xc.sell.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenId(String openId);
}
