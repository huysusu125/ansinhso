package com.huytd.ansinhso.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huytd.ansinhso.dto.weather.WeatherAirQualityResponse;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
@RequiredArgsConstructor
public class WeatherDataConverter implements AttributeConverter<WeatherAirQualityResponse, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(WeatherAirQualityResponse weatherAirQualityResponse) {
        if (weatherAirQualityResponse == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(weatherAirQualityResponse);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting Location to JSON", e);
        }
    }

    @Override
    public WeatherAirQualityResponse convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(s, WeatherAirQualityResponse.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON to Location", e);
        }
    }
}
