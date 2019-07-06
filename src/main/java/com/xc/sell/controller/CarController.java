package com.xc.sell.controller;

import com.xc.sell.dto.CarDTO;
import com.xc.sell.form.CarForm;
import com.xc.sell.service.CarInfoService;
import com.xc.sell.util.ResultVOUtil;
import com.xc.sell.viewObject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer/car")
@Slf4j
public class CarController {
    @Autowired
    private CarInfoService carInfoService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody CarForm carForm){
        CarDTO carDTO = carInfoService.add(carForm);
        return ResultVOUtil.success(carDTO);
    }

    @GetMapping("/list")
    public ResultVO list(@RequestParam("openId") String openId){
        List<CarDTO> carDTOS = carInfoService.list(openId);
        return ResultVOUtil.success(carDTOS);
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody CarForm carForm){
        log.info("carform={}", carForm);
        CarDTO carDTO = carInfoService.update(carForm);
        return ResultVOUtil.success(carDTO);
    }
}
