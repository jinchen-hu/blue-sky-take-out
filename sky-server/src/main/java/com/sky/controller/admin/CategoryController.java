package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Tag(name = "Category Interfaces")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @Operation(description = "add new category")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){
        log.info("create category：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(description = "category pagination query")
    public Result<PageResult<Category>> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("category pagination query：{}", categoryPageQueryDTO);
        PageResult<Category> pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping
    @Operation(description = "delete category")
    public Result<String> deleteById(Long id){
        log.info("delete category：{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @Operation(description = "update category")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @Operation(description = "toggle category status")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id){
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * @param type
     * @return
     */
    @GetMapping("/list")
    @Operation(description = "get categories by type")
    public Result<List<Category>> list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
