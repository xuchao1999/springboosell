package com.xc.sell.enums;

import lombok.Getter;

@Getter
public enum ConstantsKit {
    TOKEN_EXPIRE_TIME(60 * 10),
    TOKEN_RESET_TIME(1000 * 100),
    ;


    private long token_expire_time;

    ConstantsKit(long token_expire_time) {
        this.token_expire_time = token_expire_time;
    }
}
