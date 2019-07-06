package com.xc.sell.service.impl;

import com.xc.sell.dataobject.OrderDetail;
import com.xc.sell.dataobject.OrderMaster;
import com.xc.sell.dto.OrderDTO;
import com.xc.sell.util.DateUtil;
import com.xc.sell.viewObject.OrderVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterServiceImpl orderMasterService;


    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();

        orderDTO.setAddressId(0);

        orderDTO.setOpenId("33333333");

        orderDetail.setProductId("1223756");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);

        Assert.assertNotNull(orderMasterService.create(orderDTO));
    }

    @Test
    public void cancel() {
//        orderMasterService.cancel("15565408894931775170");
    }

    @Test
    public void findByOpenId(){
        List<OrderDTO> result = orderMasterService.findByOpenId("324234234");
        Assert.assertEquals("15565406655451443178", result.get(0).getOrderId());

    }

    @Test
    public void paid(){
        OrderMaster result = orderMasterService.paid("15565406655451443178");
        Assert.assertEquals(Integer.valueOf(1), result.getPayStatus());
    }

    @Test
    public void findAll(){
        List<OrderDTO> result = orderMasterService.findAll();
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void findOne(){
//        OrderDTO result = orderMasterService.findOne("15565339618181311700");
//        Assert.assertEquals("1223756", result.getOrderDetailList().get(0).getProductId());
    }

}