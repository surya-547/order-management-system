package com.ordermanagement.deliveryservice.serviceImpl;

import com.ordermanagement.deliveryservice.dtos.requestDto.DeliveryRequestDto;
import com.ordermanagement.deliveryservice.dtos.responseDto.DeliveryResponseDto;
import com.ordermanagement.deliveryservice.entity.Delivery;
import com.ordermanagement.deliveryservice.exception.DeliveryNotFoundException;
import com.ordermanagement.deliveryservice.mapper.DeliveryMapper;
import com.ordermanagement.deliveryservice.repository.DeliveryRepository;
import com.ordermanagement.deliveryservice.service.DeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class DeliveryServiceImpl implements DeliveryService {


    private final DeliveryRepository deliveryRepository;

    private final DeliveryMapper deliveryMapper;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
    }


    @Override
    public DeliveryResponseDto createDelivery(DeliveryRequestDto deliveryRequestDto) {

        Delivery delivery = new Delivery();
        delivery.setOrderId(deliveryRequestDto.getOrderId());
        delivery.setAddress(deliveryRequestDto.getAddress());
        delivery.setUserId(deliveryRequestDto.getUserId());
        delivery.setDeliveryStatus(Delivery.Status.ASSIGNED);
        LocalDateTime now = LocalDateTime.now();
        delivery.setCreatedAt(now);
        delivery.setUpdatedAt(now);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return deliveryMapper.toResponseDto(savedDelivery);

    }

    @Override
    public DeliveryResponseDto updateDelivery(Long id, DeliveryRequestDto deliveryRequestDto) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(()-> new DeliveryNotFoundException("Delivery not found with id: " + id));

        delivery.setAddress(deliveryRequestDto.getAddress());
        delivery.setUpdatedAt(LocalDateTime.now());
        deliveryRepository.save(delivery);
        return deliveryMapper.toResponseDto(delivery);

    }

    @Override
    public DeliveryResponseDto getDeliveryById(Long id) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found with id: " + id));
        return deliveryMapper.toResponseDto(delivery);
    }

    @Override
    public Page<DeliveryResponseDto> getAllDeliveries(Pageable pageable) {

        return deliveryRepository.findAll(pageable)
                .map(deliveryMapper::toResponseDto);

    }
    @Override
    public DeliveryResponseDto assignDeliveryPartner(Long id, Long deliveryPartnerId) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(()-> new DeliveryNotFoundException(" Delivery not found with id: "+ id));

        delivery.setDeliveryPartnerId(deliveryPartnerId);
        delivery.setUpdatedAt(LocalDateTime.now());
        deliveryRepository.save(delivery);
        return deliveryMapper.toResponseDto(delivery);
    }

    @Override
    public DeliveryResponseDto updateDeliveryStatus(Long id, Delivery.Status status) {

        Delivery delivery =  deliveryRepository.findById(id)
                .orElseThrow(()-> new DeliveryNotFoundException(" Delivery not Found with id: " + id));

        delivery.setUpdatedAt(LocalDateTime.now());
        delivery.setDeliveryStatus(status);
        deliveryRepository.save(delivery);
        return deliveryMapper.toResponseDto(delivery);
    }


}
