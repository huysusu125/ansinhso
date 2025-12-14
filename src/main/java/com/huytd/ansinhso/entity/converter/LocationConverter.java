package com.huytd.ansinhso.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huytd.ansinhso.entity.Location;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
@RequiredArgsConstructor
public class LocationConverter implements AttributeConverter<Location, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Location attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting Location to JSON", e);
        }
    }

    @Override
    public Location convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData, Location.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON to Location", e);
        }
    }
}
