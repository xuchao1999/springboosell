package com.xc.sell.util;


import java.util.Date;

public class TokenUtil {
    public static String createToken(String openId, String userId){
        String token = openId + "," + userId;
        return token;
    }
}
