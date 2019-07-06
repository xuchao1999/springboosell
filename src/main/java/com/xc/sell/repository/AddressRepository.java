package com.xc.sell.repository;

import com.xc.sell.dataobject.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<UserAddress, Integer> {
    List<UserAddress> findByOpenId(String openId);
}
