package com.ordermanagement.orderservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private Long userId;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private Status orderStatus;

    public enum Status{
        CREATED, CONFIRMED, CANCELLED, DELIVERED
    }

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
