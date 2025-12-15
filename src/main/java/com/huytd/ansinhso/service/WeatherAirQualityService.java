package com.huytd.ansinhso.service;

import com.huytd.ansinhso.dto.weather.AirPollutionResponse;
import com.huytd.ansinhso.dto.weather.WeatherAirQualityResponse;
import com.huytd.ansinhso.dto.weather.WeatherResponse;
import com.huytd.ansinhso.entity.Weather;
import com.huytd.ansinhso.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherAirQualityService {

    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    @Value("${openweather.base-url:https://api.openweathermap.org}")
    private String baseUrl;

    @Value("${openweather.api-key:b45c39c9b01605f5b480b20ae9c248f5}")
    private String apiKey;

    @Value("${openweather.lat:21.1539}")
    private double lat;
    @Value("${openweather.lon:106.5210}")
    private double lon;

    public WeatherAirQualityResponse getWeatherInfo() {
        return weatherRepository.findFirstByOrderByUpdatedAtDesc()
                .orElseGet(() -> Weather.builder()
                        .build())
                .getMetadata();
    }

    public void fetchWeatherInfo() {

        String weatherUrl = baseUrl + "/data/2.5/weather"
                + "?lat=" + lat
                + "&lon=" + lon
                + "&units=metric"
                + "&appid=" + apiKey;

        WeatherResponse weatherResponse =
                restTemplate.getForObject(weatherUrl, WeatherResponse.class);

        String airUrl = baseUrl + "/data/2.5/air_pollution"
                + "?lat=" + lat
                + "&lon=" + lon
                + "&appid=" + apiKey;

        AirPollutionResponse airResponse =
                restTemplate.getForObject(airUrl, AirPollutionResponse.class);
        weatherRepository.save(Weather.builder().metadata(buildResponse(weatherResponse, airResponse)).build());

    }

    private WeatherAirQualityResponse buildResponse(
            WeatherResponse weather,
            AirPollutionResponse air) {

        WeatherAirQualityResponse response = new WeatherAirQualityResponse();

        // coord (ưu tiên từ weather)
        response.setCoord(weather.getCoord());

        // weather
        response.setWeather(weather.getWeather());
        response.setBase(weather.getBase());
        response.setMain(weather.getMain());
        response.setVisibility(weather.getVisibility());
        response.setWind(weather.getWind());
        response.setClouds(weather.getClouds());
        response.setDt(weather.getDt());
        response.setSys(weather.getSys());
        response.setTimezone(weather.getTimezone());
        response.setId(weather.getId());
        response.setName(weather.getName());
        response.setCod(weather.getCod());

        // air quality
        response.setList(air.getList());

        return response;
    }
}
