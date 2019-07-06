package com.xc.sell.repository;

import com.xc.sell.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    List<OrderMaster> findByOpenId(String openId);
//    List<OrderMaster> findByOpenIdAAndOrderStatus(String openId, String orderStatus);
    OrderMaster findByOpenIdAndOrderId(String openId, String orederId);

}
