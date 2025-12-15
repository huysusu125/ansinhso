package com.huytd.ansinhso.controller.app;

import com.huytd.ansinhso.dto.weather.WeatherAirQualityResponse;
import com.huytd.ansinhso.service.WeatherAirQualityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "Weather API",
        description = "API lấy thông tin thời tiết hiện tại"
)
public class WeatherController {

    private final WeatherAirQualityService service;

    @GetMapping("/app-api/weather-air-quality")
    public WeatherAirQualityResponse getWeather() {
        return service.getWeatherInfo();
    }
}
