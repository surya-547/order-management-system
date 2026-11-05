package com.ordermanagement.notificationservice.service;

import com.ordermanagement.notificationservice.dtos.NotificationRequestDto;
import com.ordermanagement.notificationservice.dtos.NotificationResponseDto;
import com.ordermanagement.notificationservice.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {

    public NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto);

    public NotificationResponseDto getNotificationById(Long id);

    public List<NotificationResponseDto> getNotificationsByUserId(Long userId);

    public Page getAllNotifications(Pageable pageable);

    public NotificationResponseDto updateNotificationStatus(Long id, Notification.NotificationStatus status);

    public void deleteNotification(Long id);
}
