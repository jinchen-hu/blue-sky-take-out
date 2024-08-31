package com.sky.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdersSubmitDTO implements Serializable {
    private Long addressBookId;
    private int payMethod;
    private String remark;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;
    /**
     * 1 - instant deliver
     * 2 - reserved delivery time
     */
    private Integer deliveryStatus;
    private Integer tablewareNumber;

    /**
     * 1 - based on the meal size
     * 0 - chose by user
     */
    private Integer tablewareStatus;
    private Integer packAmount;
    private BigDecimal amount;
}
