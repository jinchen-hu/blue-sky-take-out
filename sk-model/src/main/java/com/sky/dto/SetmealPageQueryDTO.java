package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SetmealPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String name;
    private Integer categoryId;

    /**
     * 1 - active
     * 2 - freezing
     */
    private Integer status;

}
