package com.ordermanagement.notificationservice.dtos;


import com.ordermanagement.notificationservice.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {

    private Long id;

    private Long userId;

    private Long orderId;

    private String message;

    private Notification.NotificationType notificationType;

    private Notification.NotificationStatus notificationStatus;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;

}
