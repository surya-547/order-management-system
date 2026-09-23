package com.ordermanagement.deliveryservice.mapper;

import com.ordermanagement.deliveryservice.dtos.requestDto.DeliveryRequestDto;
import com.ordermanagement.deliveryservice.dtos.responseDto.DeliveryResponseDto;
import com.ordermanagement.deliveryservice.entity.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {


    public Delivery toEntity(DeliveryRequestDto deliveryRequestDto){

        Delivery delivery = new Delivery();
        delivery.setUserId(deliveryRequestDto.getUserId());
        delivery.setOrderId(deliveryRequestDto.getOrderId());
        delivery.setAddress(deliveryRequestDto.getAddress());

        return delivery;
    }

    public DeliveryResponseDto toResponseDto(Delivery delivery){

        DeliveryResponseDto deliveryResponseDto = new DeliveryResponseDto();
        deliveryResponseDto.setId(delivery.getId());
        deliveryResponseDto.setOrderId(delivery.getOrderId());
        deliveryResponseDto.setUserId(delivery.getUserId());
        deliveryResponseDto.setDeliveryStatus(delivery.getDeliveryStatus());
        deliveryResponseDto.setDeliveryPartnerId(delivery.getDeliveryPartnerId());
        deliveryResponseDto.setAddress(delivery.getAddress());
        deliveryResponseDto.setCreatedAt(delivery.getCreatedAt());
        deliveryResponseDto.setUpdatedAt(delivery.getUpdatedAt());

        return deliveryResponseDto;
    }
}
