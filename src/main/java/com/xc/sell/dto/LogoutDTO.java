package com.xc.sell.dto;

import lombok.Data;

@Data
public class LogoutDTO {
    private String openid;

    public LogoutDTO(String openid) {
        this.openid = openid;
    }
}
