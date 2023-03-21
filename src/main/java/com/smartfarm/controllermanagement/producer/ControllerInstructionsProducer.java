package com.smartfarm.controllermanagement.producer;

import com.smartfarm.controllermanagement.model.IotSensorInstructionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.smartfarm.controllermanagement.config.KafkaProducerConfig.INSTRUCTION_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class ControllerInstructionsProducer {

    private final KafkaTemplate<String, IotSensorInstructionDto> kafkaTemplate;

    public void sendMessage(IotSensorInstructionDto instructionDto) {
        kafkaTemplate.send(INSTRUCTION_TOPIC, instructionDto);
    }
}
