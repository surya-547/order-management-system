package com.ordermanagement.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import src.main.java.com.ordermanagement.orderservice.entity.Order;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long id;

    private Long userId;

    private Order.Status orderStatus;

    private String productName;

    private Integer quantity;

    private BigDecimal price;
}
