package com.xc.sell.service.impl;

import com.xc.sell.dataobject.CarInfo;
import com.xc.sell.dataobject.ProductInfo;
import com.xc.sell.dto.CarDTO;
import com.xc.sell.form.CarForm;
import com.xc.sell.repository.CarInfoRepository;
import com.xc.sell.service.CarInfoService;
import com.xc.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CarInfoServiceImpl implements CarInfoService {

    @Autowired
    private CarInfoRepository carInfoRepository;
    @Autowired
    private ProductInfoService productInfoService;
    @Override
    public CarDTO add(CarForm carForm) {

        CarDTO carDTO = update(carForm);
        if(carDTO == null){
            carDTO = new CarDTO();
            ProductInfo productInfo = productInfoService.findOne(carForm.getProductId());
            CarInfo carInfo = new CarInfo();
            BeanUtils.copyProperties(carForm, carInfo);
            BeanUtils.copyProperties(carInfoRepository.save(carInfo), carDTO);
            carDTO.setProductInfo(productInfo);
        }
        return carDTO;
    }

    @Override
    public CarDTO delete(String openId, Integer carId) {
        return null;
    }

    @Override
    public CarDTO findOne(String openId, String productId) {
        CarInfo carInfo = carInfoRepository.findAllByOpenIdAndProductId(openId, productId);
        if(carInfo == null){
            return null;
        }
        ProductInfo productInfo = productInfoService.findOne(carInfo.getProductId());
        CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(carInfo, carDTO);
        carDTO.setProductInfo(productInfo);
        return carDTO;
    }

    @Override
    public CarDTO update(CarForm carForm) {

        CarDTO carDTO = findOne(carForm.getOpenId(), carForm.getProductId());
        if(carDTO != null){
            carDTO.setProductQuantity(carForm.getProductQuantity());
            CarInfo carInfo = new CarInfo();
            BeanUtils.copyProperties(carDTO, carInfo);
            carInfoRepository.save(carInfo);
        }
        return carDTO;

    }

    @Override
    public List<CarDTO> list(String openId) {
        List<CarInfo> carInfos = carInfoRepository.findByOpenId(openId);
        List<CarDTO> carDTOS = new ArrayList<>();
        for(CarInfo carInfo : carInfos){
            ProductInfo productInfo = productInfoService.findOne(carInfo.getProductId());
            CarDTO carDTO = new CarDTO();
            BeanUtils.copyProperties(carInfo, carDTO);
            carDTO.setProductInfo(productInfo);
            carDTOS.add(carDTO);
        }
        return carDTOS;
    }
}
