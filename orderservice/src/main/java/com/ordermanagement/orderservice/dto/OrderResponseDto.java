package src.main.java.com.ordermanagement.orderservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import src.main.java.com.ordermanagement.orderservice.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;

    private Long userId;

    private BigDecimal totalAmount;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Order.Status orderStatus;


}
