package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {

    private int page;
    private int pageSize;

    private String name;

    // 1-dish category 2-combo category
    private Integer type;

}
