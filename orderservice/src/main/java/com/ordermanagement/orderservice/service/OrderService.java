package com.ordermanagement.orderservice.service;

import com.ordermanagement.orderservice.dto.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface OrderService {

    public OrderResponseDto createOrder(com.ordermanagement.orderservice.dto.OrderRequestDto orderRequestDto);

    public OrderResponseDto getOrderById(Long id);

   public Page<OrderResponseDto> getAllOrders(Pageable pageable);

   public OrderResponseDto cancelOrder(Long id);

    public OrderResponseDto updateOrder(Long id, com.ordermanagement.orderservice.dto.OrderRequestDto orderRequestDto);

    public List<OrderResponseDto> findByUserId(Long userId);
}