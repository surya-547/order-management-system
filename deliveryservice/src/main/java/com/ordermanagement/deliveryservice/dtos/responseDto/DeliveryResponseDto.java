package com.ordermanagement.deliveryservice.dtos.responseDto;


import com.ordermanagement.deliveryservice.entity.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseDto {

    private Long id;

    private Long orderId;

    private Long userId;

    private Long deliveryPartnerId;

    private String address;

    private Delivery.Status deliveryStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
