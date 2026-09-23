package com.ordermanagement.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(DeliveryNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(DeliveryNotFoundException deliveryNotFoundException){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deliver not Found with id:" +deliveryNotFoundException.getMessage());
    }
}
