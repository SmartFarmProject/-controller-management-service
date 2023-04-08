package com.smartfarm.sensordata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotSensorProcessedDto implements Serializable {

    private String sensorId;
    private String type;
    private double value;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSx")
    private OffsetDateTime timestamp;
    private String unit;
    private String farmUnitId;
}
