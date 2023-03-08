package com.smartfarm.controllermanagement.service;

import com.smartfarm.controllermanagement.producer.ControllerInstructionsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ControllerManagementService {

    private final NotificationService notificationService;
    private final ControllerInstructionsProducer controllerInstructionsProducer;

    public void createInstruction(String data) {
        // todo: add logic to create instruction based on data
        sendLightSwitchedNotification();

        controllerInstructionsProducer.sendMessage("{\"switch_light\" : true}");
    }

    private void sendLightSwitchedNotification() {
        notificationService.sendNotification("Light is switched");
    }
}
