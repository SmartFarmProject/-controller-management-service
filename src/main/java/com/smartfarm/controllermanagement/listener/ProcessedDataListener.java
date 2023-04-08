package com.smartfarm.controllermanagement.listener;

import com.smartfarm.sensordata.model.IotSensorProcessedDto;
import com.smartfarm.controllermanagement.service.ControllerManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.smartfarm.controllermanagement.config.KafkaConsumerConfig.GROUP_ID;
import static com.smartfarm.controllermanagement.config.KafkaConsumerConfig.PROCESSED_DATA_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessedDataListener {

    private final ControllerManagementService controllerManagementService;

    @KafkaListener(topics = PROCESSED_DATA_TOPIC, groupId = GROUP_ID, containerFactory = "listenerFactory")
    public void listen(IotSensorProcessedDto data) {
        log.info("Processed data: {} is received. Creating instruction...", data);
        controllerManagementService.createInstruction(data);
    }
}
