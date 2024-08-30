package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Tag(name = "Dish interfaces")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    @Operation(description = "create dish")
    public Result<?> save(@RequestBody DishDTO dishDTO) {
        log.info("create new dish:{}", dishDTO);

        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(description = "dish pagination query")
    public Result<PageResult<DishVO>> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("dish pagination query:{}", dishPageQueryDTO);
        PageResult<DishVO> pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @Operation(description = "delete dish")
    public Result<?> delete(@RequestParam List<Long> ids) {
        log.info("delete dish:{}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }
}
