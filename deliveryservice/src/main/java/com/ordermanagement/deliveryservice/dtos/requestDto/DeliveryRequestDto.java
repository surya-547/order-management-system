package com.ordermanagement.deliveryservice.dtos.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestDto {

    @NotNull
    @Positive
    private Long orderId;

    @NotBlank
    private String address;

    @NotNull
    @Positive
    private Long userId;

}
