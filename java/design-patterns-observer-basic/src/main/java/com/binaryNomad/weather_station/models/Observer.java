package com.binaryNomad.weather_station.models;

public interface Observer {
    void update(float temperature, float humidity, float pressure);
}
