package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Tag(name = "Employee Interfaces")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @Operation(description = "employee login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("Employee login：{}", employeeLoginDTO);
        Employee employee = employeeService.login(employeeLoginDTO);

        // Generate JWT
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * @return
     */
    @PostMapping("/logout")
    @Operation(description = "employee logout")
    public Result<String> logout() {
        return Result.success();
    }

    @Operation(description = "Create new employee")
    @PostMapping
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Create employee : {}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(description = "employee pagination query")
    public Result<PageResult<Employee>> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("Employee page query : {}", employeePageQueryDTO);
        PageResult<Employee> pageResult = employeeService.pageQuery(employeePageQueryDTO);

        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @Operation(description = "toggle employee account status")
    public Result<String> toggleAccountStatus(@PathVariable(value = "status") Integer status, Long id) {
        log.info("Toggle employee '{}' to status : {}", id, status);
        employeeService.toggleAccountStatus(status, id);
        return Result.success();
    }

    @GetMapping("/{id}")
    @Operation(description = "get employee info by id")
    public Result<Employee> getEmployeeById(@PathVariable(value = "id") Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    @PutMapping
    @Operation(description = "update employee info")
    public Result<String> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Update employee : {}", employeeDTO);
        employeeService.update(employeeDTO);

        return Result.success();
    }
}
