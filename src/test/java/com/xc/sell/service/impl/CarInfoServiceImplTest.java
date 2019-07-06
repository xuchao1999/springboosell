package com.xc.sell.service.impl;

import com.xc.sell.dto.CarDTO;
import com.xc.sell.form.CarForm;
import com.xc.sell.service.CarInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarInfoServiceImplTest {

    @Autowired
    private CarInfoService carInfoService;
    @Test
    public void add() {
        CarForm carForm = new CarForm("15565408894931775170", "1228756", 1);
        CarDTO result = carInfoService.add(carForm);
        Assert.assertNotNull(result);
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void list() {
        List<CarDTO> carDTOS = carInfoService.list("15565408894931775170");
        Assert.assertEquals(3, carDTOS.size());
    }
}