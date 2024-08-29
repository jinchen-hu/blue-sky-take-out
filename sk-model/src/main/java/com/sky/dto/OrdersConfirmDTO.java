package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersConfirmDTO implements Serializable {

    private Long id;
    /**
     * 1 - pending payment
     * 2 - pending accepted order
     * 3 - order accepted
     * 4 - delivering
     * 5 - order completed
     * 6 - cancelled
     * 7 - refunding
     */
    private Integer status;
}
