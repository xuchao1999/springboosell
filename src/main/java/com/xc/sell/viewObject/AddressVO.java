package com.xc.sell.viewObject;

import com.xc.sell.dataobject.UserInfo;
import lombok.Data;

@Data
public class AddressVO {
    private Integer addressId;
    private String openId;
    private String country = "china";
    private String province;
    private String city;
    private String county;
    private String addressDetail;
    private String name;
    private String phone;
    private String wholeAddress;

}
