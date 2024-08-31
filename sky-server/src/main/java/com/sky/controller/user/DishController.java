package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Tag(name = "client dish interfaces")
public class DishController {
    @Autowired
    private DishService dishService;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, List<DishVO>> valueOperations;

    /**
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @Operation
    public Result<List<DishVO>> list(Long categoryId) {

        // cache dishes to set
        String key = "dish_" + categoryId;

        List<DishVO> list = valueOperations.get(key);
        if (list != null && !list.isEmpty()) {
            return Result.success(list);
        }


        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);

        list = dishService.listWithFlavor(dish);

        valueOperations.set(key, list);
        return Result.success(list);
    }
}
