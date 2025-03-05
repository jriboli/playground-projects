package com.binaryNomad.weather_station.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherPayload {

    private float temperature;
    private float humidity;
    private float pressure;

}
