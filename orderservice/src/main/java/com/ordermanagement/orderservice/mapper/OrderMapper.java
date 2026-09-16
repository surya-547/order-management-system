package com.ordermanagement.orderservice.mapper;

import com.ordermanagement.orderservice.dto.OrderResponseDto;
import com.ordermanagement.orderservice.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(com.ordermanagement.orderservice.dto.OrderRequestDto orderRequestDto) {

        Order order = new Order();

        order.setUserId(orderRequestDto.getUserId());
        order.setPrice(orderRequestDto.getPrice());
        order.setQuantity(orderRequestDto.getQuantity());
        order.setProductName(orderRequestDto.getProductName());

        return order;
    }

    public OrderResponseDto toResponseDto(Order order){

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setUserId(order.getUserId());
        orderResponseDto.setProductName(order.getProductName());
        orderResponseDto.setTotalAmount(order.getTotalAmount());
        orderResponseDto.setQuantity(order.getQuantity());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setUpdatedAt(order.getUpdatedAt());
        return orderResponseDto;
    }
}
