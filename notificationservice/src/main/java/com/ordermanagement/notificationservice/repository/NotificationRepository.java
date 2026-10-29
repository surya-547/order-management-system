package com.ordermanagement.notificationservice.repository;

import com.ordermanagement.notificationservice.dtos.NotificationResponseDto;
import com.ordermanagement.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    public List<Notification> findByUserId(Long userId);
}
