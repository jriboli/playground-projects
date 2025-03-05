package com.binaryNomad.weather_station.config;

import com.binaryNomad.weather_station.models.CurrentConditionDisplay;
import com.binaryNomad.weather_station.models.WeatherData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public WeatherData weatherData() {
        return new WeatherData();
    }

    @Bean
    public CurrentConditionDisplay currentConditionDisplay(WeatherData weatherData) {
        CurrentConditionDisplay display = new CurrentConditionDisplay();
        weatherData.registerObserver(display);
        return display;
    }
}
