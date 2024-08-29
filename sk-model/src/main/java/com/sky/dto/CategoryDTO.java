package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    // Primary key
    private Long id;

    // 1-dish category 2-combo category
    private Integer type;

    private String name;

    private Integer sort;

}
