package com.ordermanagement.notificationservice.dtos;


import com.ordermanagement.notificationservice.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto {

    private Long userId;

    private Long orderId;

    private String message;

    private Notification.NotificationType notificationType;

    private Notification.NotificationStatus notificationStatus;

}

