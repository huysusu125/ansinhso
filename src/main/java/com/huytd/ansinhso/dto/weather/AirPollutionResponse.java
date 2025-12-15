package com.huytd.ansinhso.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirPollutionResponse {
    private Coord coord;
    private List<AirQualityItem> list;
}
