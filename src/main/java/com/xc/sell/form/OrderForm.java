package com.xc.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class OrderForm {
    @NotNull
    private String openId;

    @NotNull
    private Integer addressId;

    @NotNull
    private String items;
}
