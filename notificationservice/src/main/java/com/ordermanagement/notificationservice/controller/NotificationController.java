package com.ordermanagement.notificationservice.controller;

import com.ordermanagement.notificationservice.dtos.NotificationRequestDto;
import com.ordermanagement.notificationservice.dtos.NotificationResponseDto;
import com.ordermanagement.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public NotificationResponseDto createNotification(@RequestBody NotificationRequestDto notificationRequestDto){
        return notificationService.createNotification(notificationRequestDto);
    }

    @GetMapping("/{id}")
    public NotificationResponseDto getNotificationById(@PathVariable Long id){

        return notificationService.getNotificationById(id);
    }

    @GetMapping("/userId/{userId}")
    public List<NotificationResponseDto> getNotificationByUserId(@PathVariable Long userId){

        return notificationService.getNotificationsByUserId(userId);
    }
}
