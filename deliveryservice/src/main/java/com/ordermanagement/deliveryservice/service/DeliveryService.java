package com.ordermanagement.deliveryservice.service;

import com.ordermanagement.deliveryservice.dtos.requestDto.DeliveryRequestDto;
import com.ordermanagement.deliveryservice.dtos.responseDto.DeliveryResponseDto;
import com.ordermanagement.deliveryservice.entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryService {



    public DeliveryResponseDto createDelivery(DeliveryRequestDto deliveryRequestDto);

    public DeliveryResponseDto updateDelivery(Long id, DeliveryRequestDto deliveryRequestDto);

    public DeliveryResponseDto getDeliveryById(Long id);

    public Page<DeliveryResponseDto> getAllDeliveries(Pageable pageable);

    public DeliveryResponseDto assignDeliveryPartner(Long id, Long deliveryPartnerId);

    public DeliveryResponseDto updateDeliveryStatus(Long id, Delivery.Status status);

}
