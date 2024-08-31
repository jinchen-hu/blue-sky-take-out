package com.sky.controller.admin;

import com.sky.constant.CacheNamesConstant;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Tag(name = "combo interfaces")
@Slf4j
public class SetMealController {
    @Autowired
    private SetMealService setMealService;

    @PostMapping
    @Operation
    @CacheEvict(cacheNames = CacheNamesConstant.SET_MEAL_CACHE, key = "#setmealDTO.categoryId")
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

    @DeleteMapping
    @Operation
    @CacheEvict(cacheNames = CacheNamesConstant.SET_MEAL_CACHE, allEntries = true)
    public Result<?> delete(@RequestParam List<Long> ids) {
        setMealService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    @Operation(description = "get combo by id")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealVO setmealVO = setMealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /**
     *
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @Operation(description = "update combo")
    @CacheEvict(cacheNames = CacheNamesConstant.SET_MEAL_CACHE, allEntries = true)
    public Result<?> update(@RequestBody SetmealDTO setmealDTO) {
        setMealService.update(setmealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @Operation(description = "toggle combo status")
    @CacheEvict(cacheNames = CacheNamesConstant.SET_MEAL_CACHE, allEntries = true)
    public Result<?> startOrStop(@PathVariable Integer status, Long id) {
        setMealService.startOrStop(status, id);
        return Result.success();
    }
}
