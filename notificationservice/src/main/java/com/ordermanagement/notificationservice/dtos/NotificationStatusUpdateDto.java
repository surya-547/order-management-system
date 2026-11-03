package com.ordermanagement.notificationservice.dtos;

import com.ordermanagement.notificationservice.entity.Notification;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationStatusUpdateDto {

    @NotNull
    private Notification.NotificationStatus notificationStatus;
}
