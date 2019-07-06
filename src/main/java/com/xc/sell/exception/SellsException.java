package com.xc.sell.exception;

import com.xc.sell.enums.ResultEnum;
import lombok.Getter;

@Getter
public class SellsException extends RuntimeException {
    private Integer code;

    public SellsException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public SellsException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
