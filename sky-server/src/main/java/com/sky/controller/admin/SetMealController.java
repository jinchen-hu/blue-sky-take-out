package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/setmeal")
@Tag(name = "combo interfaces")
@Slf4j
public class SetMealController {
    @Autowired
    private SetMealService setMealService;

    @PostMapping
    @Operation
    public Result<?> save(@RequestBody SetmealDTO setmealDTO) {
        setMealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @Operation
    public Result<?> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult<SetmealVO> pageResult= setMealService.pageQuery(setmealPageQueryDTO);

        return Result.success(pageResult);
    }
}
