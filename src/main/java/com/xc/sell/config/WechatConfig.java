package com.xc.sell.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class WechatConfig {
    private String appid = "wx3099e22e6dcb8d58";

    private String secret = "087e35d25df0aa70db1e2069ebac3fff";

    private String mch_id = "1503825991";

    private String partner_key = "7B577E935B4ED0809F989052B4D29948";

    private String notify_url = "http://127.0.0.1:8360/api/pay/notify";
}
