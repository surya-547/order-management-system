package com.ordermanagement.notificationservice.controller;

import com.ordermanagement.notificationservice.dtos.NotificationRequestDto;
import com.ordermanagement.notificationservice.dtos.NotificationResponseDto;
import com.ordermanagement.notificationservice.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<NotificationResponseDto> createNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.createNotification(notificationRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDto> getNotificationById(@PathVariable Long id) {

        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationByUserId(@PathVariable Long userId) {

        return ResponseEntity.ok(notificationService.getNotificationsByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<Page<NotificationResponseDto>> getAllNotifications(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.ok(notificationService.getAllNotifications(pageRequest));
    }
}