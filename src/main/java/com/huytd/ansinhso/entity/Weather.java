package com.huytd.ansinhso.entity;

import com.huytd.ansinhso.dto.weather.WeatherAirQualityResponse;
import com.huytd.ansinhso.entity.converter.WeatherDataConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "weather")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class Weather extends BaseEntity {

    @Column(name = "metadata")
    @Convert(converter = WeatherDataConverter.class)
    private WeatherAirQualityResponse metadata;

}
