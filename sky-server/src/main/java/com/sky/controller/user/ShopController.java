package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Tag(name = "shop interfaces")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";


    @Resource(name = "redisTemplate")
    private ValueOperations<String, Integer> valueOperations;

    @GetMapping("/status")
    @Operation
    public Result<Integer> getStatus() {
        Integer status = valueOperations.get(KEY);
        assert status != null;
        log.info("Current shop status is {}", status == 1 ? "open" : "closed");
        return Result.success(status);
    }
}