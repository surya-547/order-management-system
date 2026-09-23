package com.ordermanagement.deliveryservice.repository;

import com.ordermanagement.deliveryservice.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
