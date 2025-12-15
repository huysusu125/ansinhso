package com.huytd.ansinhso.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirQualityItem {

    private AirQualityMain main;
    private AirQualityComponents components;
    private Long dt;
}
