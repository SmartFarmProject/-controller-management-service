package com.smartfarm.controllermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotSensorInstructionDto {

    private String farmUnitId;
    private boolean switchLight;
    private boolean switchWater;
}
