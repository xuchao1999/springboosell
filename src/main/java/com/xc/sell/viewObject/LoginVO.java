package com.xc.sell.viewObject;

import com.xc.sell.dto.InUserInfo;
import lombok.Data;

@Data
public class LoginVO {
    private InUserInfo userInfo;

    private String token;

    private String openid;
}
