package com.ordermanagement.notificationservice.service;

import com.ordermanagement.notificationservice.dtos.NotificationRequestDto;
import com.ordermanagement.notificationservice.dtos.NotificationResponseDto;

public interface NotificationService {

    public NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto);

}
