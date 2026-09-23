package com.ordermanagement.deliveryservice.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestDto {

    private Long orderId;

    private String address;

    private Long userId;

}
