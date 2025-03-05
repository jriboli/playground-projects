package com.binaryNomad.weather_station.controller;

import com.binaryNomad.weather_station.models.WeatherData;
import com.binaryNomad.weather_station.models.WeatherPayload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private WeatherData weatherData;

    public WeatherController(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    @PostMapping()
    public void updateWeather(
            @RequestBody WeatherPayload payload) {
        weatherData.setMeasurements(payload.getTemperature(), payload.getHumidity(), payload.getPressure());
    }
}
