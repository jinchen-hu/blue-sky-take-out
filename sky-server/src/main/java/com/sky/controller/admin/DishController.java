package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dish")
@Tag(name = "Dish interfaces")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    @Operation(description = "create dish")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("create new dish:{}", dishDTO);

        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }
}
