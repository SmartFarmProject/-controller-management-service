package com.smartfarm.controllermanagement.service;

import com.smartfarm.controllermanagement.model.IotSensorInstructionDto;
import com.smartfarm.sensordata.model.IotSensorProcessedDto;
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

    private static final String LIGHT_SENSOR_TYPE = "LIGHT";
    private static final String SOIL_HUMIDITY_SENSOR_TYPE = "SOIL_HUMIDITY";


    private final NotificationService notificationService;
    private final ControllerInstructionsProducer controllerInstructionsProducer;

    public void createInstruction(IotSensorProcessedDto data) {
        if (data.getType().equals(LIGHT_SENSOR_TYPE)) {
            IotSensorInstructionDto iotSensorInstructionDto = prepareSwitchLightInstruction(data);
            controllerInstructionsProducer.sendMessage(iotSensorInstructionDto);
            notificationService.sendNotification(iotSensorInstructionDto);
        } else if (data.getType().equals(SOIL_HUMIDITY_SENSOR_TYPE)) {
            IotSensorInstructionDto iotSensorInstructionDto = prepareSwitchWaterInstruction(data);
            controllerInstructionsProducer.sendMessage(iotSensorInstructionDto);
            notificationService.sendNotification(iotSensorInstructionDto);
        }
    }

    private IotSensorInstructionDto prepareSwitchLightInstruction(IotSensorProcessedDto data) {
        return IotSensorInstructionDto.builder()
                .farmUnitId(data.getFarmUnitId())
                .switchLight(data.getValue() > 400)
                .switchWater(false)
                .build();
    }

    private IotSensorInstructionDto prepareSwitchWaterInstruction(IotSensorProcessedDto data) {
        return IotSensorInstructionDto.builder()
                .farmUnitId(data.getFarmUnitId())
                .switchLight(false)
                .switchWater(data.getValue() > 40)
                .build();
    }
}
