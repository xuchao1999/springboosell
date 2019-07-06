package com.xc.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UserAddress {
    @Id
    @GeneratedValue
    private Integer addressId;
    private String openId;
    private String country = "china";
    private String province;
    private String city;
    private String county;
    private String addressDetail;
    private String name;
    private String phone;

    public String wholeAddress(){
        return province + city + county + addressDetail;
    }
}
