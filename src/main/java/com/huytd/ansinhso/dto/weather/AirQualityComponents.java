package com.huytd.ansinhso.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirQualityComponents {
    private Double co;
    private Double no;
    private Double no2;
    private Double o3;
    private Double so2;
    @JsonProperty("pm2_5")
    private Double pm25;
    private Double pm10;
    private Double nh3;
}
