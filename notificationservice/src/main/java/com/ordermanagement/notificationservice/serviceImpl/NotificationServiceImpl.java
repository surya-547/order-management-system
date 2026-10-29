package com.ordermanagement.notificationservice.serviceImpl;

import com.ordermanagement.notificationservice.dtos.NotificationRequestDto;
import com.ordermanagement.notificationservice.dtos.NotificationResponseDto;
import com.ordermanagement.notificationservice.entity.Notification;
import com.ordermanagement.notificationservice.mapper.NotificationMapper;
import com.ordermanagement.notificationservice.repository.NotificationRepository;
import com.ordermanagement.notificationservice.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto) {
        Notification notification = notificationMapper.toEntity(notificationRequestDto);
        notification.setNotificationStatus(Notification.NotificationStatus.PENDING);
        notification.setCreatedAt(LocalDateTime.now());
        Notification savedNotification = notificationRepository.save(notification);

        return notificationMapper.toResponseDto(savedNotification);

    }

    @Override
    public NotificationResponseDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" Notification Not Found"));

        return notificationMapper.toResponseDto(notification);
    }

    @Override
    public List<NotificationResponseDto> getNotificationsByUserId(Long userId) {

        List<Notification> notifications = notificationRepository.findByUserId(userId);

        List<NotificationResponseDto> listOfNotifications = notifications
                .stream()
                .map(notificationMapper::toResponseDto)
                .toList();

        return listOfNotifications;
    }

}