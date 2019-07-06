package com.xc.sell.repository;

import com.xc.sell.dataobject.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarInfoRepository extends JpaRepository<CarInfo, Integer> {
    List<CarInfo> findByOpenId(String openId);
    CarInfo findAllByOpenIdAndProductId(String openId, String productId);
}
