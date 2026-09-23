package com.ordermanagement.deliveryservice.controller;

import com.ordermanagement.deliveryservice.dtos.requestDto.DeliveryRequestDto;
import com.ordermanagement.deliveryservice.dtos.responseDto.DeliveryResponseDto;
import com.ordermanagement.deliveryservice.service.DeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryController {


    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<DeliveryResponseDto> createDelivery(@RequestBody DeliveryRequestDto deliveryRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.createDelivery(deliveryRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDto> getDeliveryById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(deliveryService.getDeliveryById(id));
    }

    @GetMapping
    public ResponseEntity<Page<DeliveryResponseDto>> getAllDeliveries(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "5")  int size){

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(deliveryService.getAllDeliveries(pageable));
    }

    @GetMapping("/{id}/{deliveryPartnerId}")
    public ResponseEntity<DeliveryResponseDto> assignDeliveryPartner(@PathVariable Long id, @PathVariable Long deliveryPartnerId){

        return ResponseEntity.status(HttpStatus.OK).body(deliveryService.assignDeliveryPartner(id, deliveryPartnerId));
    }
}
