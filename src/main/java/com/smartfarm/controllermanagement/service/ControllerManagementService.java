package com.smartfarm.controllermanagement.service;

import com.smartfarm.controllermanagement.model.IotSensorInstructionDto;
import com.smartfarm.controllermanagement.model.IotSensorProcessedDto;
import com.smartfarm.controllermanagement.model.NotificationDto;
import com.smartfarm.controllermanagement.producer.ControllerInstructionsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ControllerManagementService {

    private final NotificationService notificationService;
    private final ControllerInstructionsProducer controllerInstructionsProducer;

    public void createInstruction(IotSensorProcessedDto data) {
        controllerInstructionsProducer.sendMessage(prepareInstruction(data));

        sendLightSwitchedNotification();
    }

    private IotSensorInstructionDto prepareInstruction(IotSensorProcessedDto data) {
        return IotSensorInstructionDto.builder()
                .farmUnitId(data.getFarmUnitId())
                .switchLight(new Random().nextBoolean()) // todo: add some logic for this
                .switchWater(new Random().nextBoolean()) // todo: add some logic for this
                .build();
    }

    private void sendLightSwitchedNotification() {
        notificationService.sendNotification(NotificationDto.builder()
                .id(UUID.randomUUID().toString())
                .text("Light is switched")
                .build());
    }
}
