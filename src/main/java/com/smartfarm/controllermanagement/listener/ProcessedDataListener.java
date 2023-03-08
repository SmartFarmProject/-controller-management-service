package com.smartfarm.controllermanagement.listener;

import com.smartfarm.controllermanagement.service.ControllerManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessedDataListener {

    private final String PROCESSED_DATA_TOPIC = "iot-processed-data";
    private final ControllerManagementService controllerManagementService;

    @KafkaListener(topics = PROCESSED_DATA_TOPIC, groupId = "groupId")
    public void listen(String data) {
        log.info("Processed data: {} is received", data);
        controllerManagementService.createInstruction(data);
    }
}
