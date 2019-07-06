package com.xc.sell.service.impl;

import com.xc.sell.dataobject.UserAddress;
import com.xc.sell.enums.ResultEnum;
import com.xc.sell.exception.SellsException;
import com.xc.sell.repository.AddressRepository;
import com.xc.sell.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public UserAddress add(UserAddress userAddress) {
        return repository.save(userAddress);
    }

    @Override
    public UserAddress delete(Integer addressId) {
        UserAddress userAddress = findById(addressId);
        repository.delete(userAddress);
        return userAddress;
    }

    @Override
    public UserAddress update(UserAddress userAddress) {

        UserAddress newAddress = new UserAddress();
        BeanUtils.copyProperties(userAddress, newAddress);

        return repository.save(newAddress);
    }

    @Override
    public UserAddress findById(Integer addressId) {
        UserAddress userAddress = repository.findById(addressId).get();

        if(userAddress == null){
            log.error("【用户地址删除】 地址不存在");
            throw new SellsException(ResultEnum.ADDRESS_NOT_EXIST);
        }
        return userAddress;
    }

    @Override
        public List<UserAddress> findByOpenId(String openId) {
            return repository.findByOpenId(openId);
    }
}
