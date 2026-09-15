package src.main.java.com.ordermanagement.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.main.java.com.ordermanagement.orderservice.dto.OrderResponseDto;
import src.main.java.com.ordermanagement.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody com.ordermanagement.orderservice.dto.OrderRequestDto orderRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
    }
}
