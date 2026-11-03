package com.ordermanagement.notificationservice.dtos;


import com.ordermanagement.notificationservice.entity.Notification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto {

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long orderId;

    @NotBlank
    private String message;

    @NotNull
    private Notification.NotificationType notificationType;

}

