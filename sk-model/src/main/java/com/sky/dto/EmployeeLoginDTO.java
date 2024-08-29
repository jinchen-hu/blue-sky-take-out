package com.sky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;

@Data
public class EmployeeLoginDTO implements Serializable {

    private String username;
    private String password;

}
