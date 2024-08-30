package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

public interface SetMealService {
    void saveWithDish(SetmealDTO setmealDTO);

    SetmealVO getSetmealVOById(Long id);

    PageResult<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
