package com.xc.sell.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UserInfoDTO {
    private String errMsg;

    private String encryptedData;

    private String iv;

    private String rawData;

    private String signature;

    private InUserInfo inUserInfo;
}
