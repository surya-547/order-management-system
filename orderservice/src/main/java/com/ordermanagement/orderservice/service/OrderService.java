package src.main.java.com.ordermanagement.orderservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import src.main.java.com.ordermanagement.orderservice.dto.OrderResponseDto;


public interface OrderService {

    public OrderResponseDto createOrder(com.ordermanagement.orderservice.dto.OrderRequestDto orderRequestDto);

    public OrderResponseDto getOrderById(Long id);

    public Page<OrderResponseDto> getAllOrders(Pageable pageable);

    public OrderResponseDto cancelOrder(Long id);

    public OrderResponseDto updateOrder(Long id, com.ordermanagement.orderservice.dto.OrderRequestDto orderRequestDto);

}