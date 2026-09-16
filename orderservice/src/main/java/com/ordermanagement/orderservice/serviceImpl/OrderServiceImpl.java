package com.ordermanagement.orderservice.serviceImpl;

import com.ordermanagement.orderservice.dto.OrderRequestDto;
import com.ordermanagement.orderservice.dto.OrderResponseDto;
import com.ordermanagement.orderservice.entity.Order;
import com.ordermanagement.orderservice.exception.OrderNotFoundException;
import com.ordermanagement.orderservice.exception.OrderStatusException;
import com.ordermanagement.orderservice.mapper.OrderMapper;
import com.ordermanagement.orderservice.repository.OrderRepository;
import com.ordermanagement.orderservice.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper){
        this.orderRepository = orderRepository;
        this.orderMapper =orderMapper;

    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        Order order = orderMapper.toEntity(orderRequestDto);

        order.setTotalAmount(BigDecimal.valueOf(orderRequestDto.getQuantity())
                .multiply(orderRequestDto.getPrice()));

        order.setOrderStatus(Order.Status.CREATED);
        LocalDateTime now = LocalDateTime.now();
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponseDto(savedOrder);

    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));

        return orderMapper.toResponseDto(order);
    }

    @Override
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(orderMapper::toResponseDto);


    }



    @Override
    public OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: "+id));
        order.setProductName(orderRequestDto.getProductName());
        order.setQuantity(orderRequestDto.getQuantity());
        order.setPrice(orderRequestDto.getPrice());
        order.setTotalAmount(
                BigDecimal.valueOf(orderRequestDto.getQuantity())
                        .multiply(orderRequestDto.getPrice()));

        order.setUpdatedAt(LocalDateTime.now());

        Order updatedOrder = orderRepository.save(order);

        return orderMapper.toResponseDto(updatedOrder);
    }



    @Override
    public OrderResponseDto cancelOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found with id: " + id));

        if(order.getOrderStatus() == Order.Status.CANCELLED){
            throw new OrderStatusException("Order already cancelled with id:" + id);
        }
        if(order.getOrderStatus() == Order.Status.DELIVERED){
            throw new OrderStatusException("Delivered Order cannot be cancelled with id:" + id);
        }
        order.setOrderStatus(Order.Status.CANCELLED);
        order.setUpdatedAt(LocalDateTime.now());
        Order cancelledOrder = orderRepository.save(order);

        return orderMapper.toResponseDto(cancelledOrder);
    }

    @Override
    public List<OrderResponseDto> findByUserId(Long userId) {

        List<Order> orders = orderRepository.findByUserId(userId);

        List<OrderResponseDto> orderList = orders
                .stream()
                .map(orderMapper::toResponseDto)
                .toList();

        return orderList;
    }
}
