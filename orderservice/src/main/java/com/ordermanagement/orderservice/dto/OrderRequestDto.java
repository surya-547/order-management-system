package com.ordermanagement.orderservice.dto;

import com.ordermanagement.orderservice.entity.Order;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long id;

    @Valid
    private Long userId;

    private Order.Status orderStatus;

    @Valid
    private String productName;

    private Integer quantity;

    private BigDecimal price;
}
