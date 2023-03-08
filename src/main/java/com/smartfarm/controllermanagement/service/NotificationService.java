package com.smartfarm.controllermanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationService {

    public void sendNotification(String notification) {
        // todo: add logic to send notification to Notification Microservice
        log.info("Notification: {} is sent", notification);
    }
}
