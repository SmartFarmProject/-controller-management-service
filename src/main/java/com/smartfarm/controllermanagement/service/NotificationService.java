package com.smartfarm.controllermanagement.service;

import com.smartfarm.controllermanagement.model.IotSensorInstructionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.POST;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationService {

    private static final String NOTIFICATION_PATH = "/notification";

    private final RestTemplate restTemplate;

    @Value(value = "${smart.farm.notification-service-host}")
    private String notificationServiceHost;

    public void sendNotification(IotSensorInstructionDto instructionDto) {
        log.info("Notification: {} is sent", instructionDto);
        String url = notificationServiceHost + NOTIFICATION_PATH;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<IotSensorInstructionDto> request = new HttpEntity<>(instructionDto, headers);

        log.info("Sending notification by calling: {}", url);
        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Response status from notification service: {}", response.getStatusCode());
        } else {
            log.error("Request failed with status code: " + response.getStatusCode());
        }
    }
}
