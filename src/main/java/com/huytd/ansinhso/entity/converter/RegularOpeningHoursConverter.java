package com.huytd.ansinhso.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huytd.ansinhso.entity.RegularOpeningHours;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
@RequiredArgsConstructor
public class RegularOpeningHoursConverter implements AttributeConverter<RegularOpeningHours, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(RegularOpeningHours attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting RegularOpeningHours to JSON", e);
        }
    }

    @Override
    public RegularOpeningHours convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData, RegularOpeningHours.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON to RegularOpeningHours", e);
        }
    }
}