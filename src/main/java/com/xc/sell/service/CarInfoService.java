package com.xc.sell.service;

import com.xc.sell.dto.CarDTO;
import com.xc.sell.form.CarForm;

import java.util.List;

public interface CarInfoService {
    /*添加购物车*/
    CarDTO add(CarForm carForm);
    /*删除购物车*/
    CarDTO delete(String openId, Integer carId);
    /*更新购物车*/
    CarDTO update(CarForm carForm);
    /*查询购物车列表*/
    List<CarDTO> list(String openId);
    /*通过openId和productId找到购物车信息*/
    CarDTO findOne(String openId, String productId);
}
