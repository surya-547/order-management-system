package com.ordermanagement.notificationservice.mapper;

import com.ordermanagement.notificationservice.dtos.NotificationRequestDto;
import com.ordermanagement.notificationservice.dtos.NotificationResponseDto;
import com.ordermanagement.notificationservice.entity.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public Notification toEntity(NotificationRequestDto notificationRequestDto){

        Notification notification = new Notification();

        notification.setNotificationType(notificationRequestDto.getNotificationType());
        notification.setNotificationStatus(notificationRequestDto.getNotificationStatus());
        notification.setMessage(notificationRequestDto.getMessage());
        notification.setOrderId(notificationRequestDto.getOrderId());
        notification.setUserId(notificationRequestDto.getUserId());
        notification.setCreatedAt(LocalDateTime.now());

        return notification;
    }

    public NotificationResponseDto toResponseDto(Notification notification){

        NotificationResponseDto notificationResponseDto = new NotificationResponseDto();

        notificationResponseDto.setId(notification.getId());
        notificationResponseDto.setUserId(notification.getUserId());
        notificationResponseDto.setOrderId(notification.getOrderId());
        notificationResponseDto.setMessage(notification.getMessage());
        notificationResponseDto.setNotificationStatus(notification.getNotificationStatus());
        notificationResponseDto.setNotificationType(notification.getNotificationType());
        notificationResponseDto.setCreatedAt(notification.getCreatedAt());
        notificationResponseDto.setSentAt(notification.getSentAt());

        return notificationResponseDto;

    }
}
