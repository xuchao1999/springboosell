package com.xc.sell.util;


import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenUtil {

    public String createToken(String openId, String userId){

        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }

    public String getToten(HttpServletRequest request){
        String token = request.getHeader("X-Nideshop-Token");
        if(token == null || token.length() == 0){
            throw new RuntimeException("token为空");
        }
        return token;
    }

}
