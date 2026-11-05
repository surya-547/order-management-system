package com.ordermanagement.notificationservice.serviceImpl;

import com.ordermanagement.notificationservice.dtos.NotificationRequestDto;
import com.ordermanagement.notificationservice.dtos.NotificationResponseDto;
import com.ordermanagement.notificationservice.entity.Notification;
import com.ordermanagement.notificationservice.exception.NotificationNotFoundException;
import com.ordermanagement.notificationservice.mapper.NotificationMapper;
import com.ordermanagement.notificationservice.repository.NotificationRepository;
import com.ordermanagement.notificationservice.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private static final Logger log =  LoggerFactory.getLogger(NotificationServiceImpl.class);

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto) {

        log.info("Creating notification for userid: {}, orderId: {}", notificationRequestDto.getUserId() ,notificationRequestDto.getOrderId());

        Notification notification = notificationMapper.toEntity(notificationRequestDto);
        notification.setNotificationStatus(Notification.NotificationStatus.PENDING);
        notification.setCreatedAt(LocalDateTime.now());
        Notification savedNotification = notificationRepository.save(notification);

        log.info("Notification created successfully. notificationId: {}", savedNotification.getId());

        return notificationMapper.toResponseDto(savedNotification);

    }

    @Override
    public NotificationResponseDto getNotificationById(Long id) {

        log.info("Fetching notification with id: {}", id);

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(" Notification Not Found with id: "+id));

        log.debug("Notification retrieved successfully. notificationId: {}", id);

        return notificationMapper.toResponseDto(notification);
    }

    @Override
    public List<NotificationResponseDto> getNotificationsByUserId(Long userId) {

        log.info("Fetching notifications for userId: {}", userId);

        List<Notification> notifications = notificationRepository.findByUserId(userId);

        log.debug("Retrieved notifications {} for userId: {}", notifications.size(), userId);

        return notifications
                .stream()
                .map(notificationMapper::toResponseDto)
                .toList();
    }

    @Override
    public Page<NotificationResponseDto> getAllNotifications(Pageable pageable) {

        log.info("Fetching all notifications");

        Page<Notification> notifications =
                notificationRepository.findAll(pageable);

        log.debug("Retrieved {} notifications. Page: {}, Page size: {}",
                notifications.getNumberOfElements(),
                notifications.getNumber(),
                notifications.getSize());

        return notifications.map(notificationMapper::toResponseDto);
    }

    @Override
    public NotificationResponseDto updateNotificationStatus(Long id, Notification.NotificationStatus status) {

        log.info("Updating notification status. notificationId: {}, newStatus: {}", id, status);

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with id: " + id));

        notification.setNotificationStatus(status);

        if(status == Notification.NotificationStatus.SENT){
            notification.setSentAt(LocalDateTime.now());
        }

        Notification savedNotification = notificationRepository.save(notification);

        log.info("Notification status updated successfully. notificationId: {}, status: {}",
                                            savedNotification.getId(),
                                            savedNotification.getNotificationStatus());

        return notificationMapper.toResponseDto(savedNotification);

    }

    @Override
    public void deleteNotification(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(" Notification not found with id: " + id));

        log.info("Notification Deleted Successfully with id: {}", id);

        notificationRepository.deleteById(id);

    }

}