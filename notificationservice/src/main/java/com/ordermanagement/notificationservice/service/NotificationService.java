package com.ordermanagement.notificationservice.service;

import com.ordermanagement.notificationservice.dtos.NotificationRequestDto;
import com.ordermanagement.notificationservice.dtos.NotificationResponseDto;

import java.util.List;

public interface NotificationService {

    public NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto);

    public NotificationResponseDto getNotificationById(Long id);

    public List<NotificationResponseDto> getNotificationsByUserId(Long userId);
}
