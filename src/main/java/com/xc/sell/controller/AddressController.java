package com.xc.sell.controller;

import com.xc.sell.dataobject.UserAddress;
import com.xc.sell.enums.ResultEnum;
import com.xc.sell.exception.SellsException;
import com.xc.sell.service.AddressService;
import com.xc.sell.service.UserInfoService;
import com.xc.sell.util.ResultVOUtil;
import com.xc.sell.viewObject.AddressVO;
import com.xc.sell.viewObject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/address")
@Slf4j
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("list")
    public ResultVO list(@RequestParam("openId") String openId){
        log.error("openId={}", openId);

        List<UserAddress> userAddresses = addressService.findByOpenId(openId);
        if(userAddresses.size() == 0){
            throw new SellsException(ResultEnum.ADDRESS_NOT_EXIST);
        }

        List<AddressVO> addressVOS = new ArrayList<>();
        for(UserAddress userAddress : userAddresses){
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(userAddress, addressVO);
            addressVO.setWholeAddress(userAddress.wholeAddress());
            addressVOS.add(addressVO);
        }

        return ResultVOUtil.success(addressVOS);
    }
}
