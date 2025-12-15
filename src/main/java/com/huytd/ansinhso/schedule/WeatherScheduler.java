package com.huytd.ansinhso.schedule;

import com.huytd.ansinhso.service.WeatherAirQualityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {
    private final WeatherAirQualityService weatherAirQualityService;

    @Scheduled(cron = "${weather.schedule.cron:0 */15 * * * *}")
    public void fetchWeatherInfo() {
        weatherAirQualityService.fetchWeatherInfo();
    }
}
