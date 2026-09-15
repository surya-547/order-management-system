package src.main.java.com.ordermanagement.orderservice.serviceImpl;

import com.ordermanagement.orderservice.dto.OrderRequestDto;
import org.springframework.stereotype.Service;
import src.main.java.com.ordermanagement.orderservice.dto.OrderResponseDto;
import src.main.java.com.ordermanagement.orderservice.entity.Order;
import src.main.java.com.ordermanagement.orderservice.exception.OrderNotFoundException;
import src.main.java.com.ordermanagement.orderservice.mapper.OrderMapper;
import src.main.java.com.ordermanagement.orderservice.repository.OrderRepository;
import src.main.java.com.ordermanagement.orderservice.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

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
}
