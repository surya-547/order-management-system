package com.ordermanagement.notificationservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long orderId;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    public enum NotificationType{
        ORDER_CREATED, ORDER_CONFIRMED, DELIVERY_ASSIGNED,
        OUT_FOR_DELIVERY, DELIVERY_DELIVERED, DELIVERY_FAILED;
    }

    public enum NotificationStatus{
        PENDING, SENT, FAILED, READ;
    }

    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;

}
