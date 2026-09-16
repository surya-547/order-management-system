package com.ordermanagement.orderservice.controller;

import com.ordermanagement.orderservice.dto.OrderRequestDto;
import com.ordermanagement.orderservice.dto.OrderResponseDto;
import com.ordermanagement.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> getAllOrders(@RequestParam(defaultValue="0") int page,
                                                               @RequestParam(defaultValue="5")int size){

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id,
                                                        @RequestBody OrderRequestDto orderRequestDto){

        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrder(id, orderRequestDto));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrder(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(@PathVariable Long userId){

        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByUserId(userId));
    }
}
