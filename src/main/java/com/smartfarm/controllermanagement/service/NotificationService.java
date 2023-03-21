package com.smartfarm.controllermanagement.service;

import com.smartfarm.controllermanagement.model.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationService {

    public void sendNotification(NotificationDto notification) {
        // todo: add logic to send notification to Notification Microservice when it's implemented
        log.info("Notification: {} is sent", notification);
    }
}
