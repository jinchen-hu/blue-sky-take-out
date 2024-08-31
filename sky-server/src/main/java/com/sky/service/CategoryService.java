package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import java.util.List;

public interface CategoryService {

    /**
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * @param id
     */
    void deleteById(Long id);

    /**
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
