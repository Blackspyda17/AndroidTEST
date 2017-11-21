package com.motivaimagine.motivaimagine_us_trial.rest_client.user.models;

import java.io.Serializable;

/**
 * Created by gpaez on 11/17/2017.
 */

public class city implements Serializable {
    private String name;
    private String weather_desc;
    private String temperature;
    private String wind_speed;
    private String humidity;

    public city(String name, String weather_desc, String temperature, String wind_speed, String humidity) {
        this.name = name;
        this.weather_desc = weather_desc;
        this.temperature = temperature;
        this.wind_speed = wind_speed;
        this.humidity=humidity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather_desc() {
        return weather_desc;
    }

    public void setWeather_desc(String weather_desc) {
        this.weather_desc = weather_desc;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
