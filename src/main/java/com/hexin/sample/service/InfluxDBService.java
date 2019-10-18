package com.hexin.sample.service;

import com.hexin.sample.entity.Aqi;
import com.hexin.sample.entity.Row;
import com.hexin.sample.entity.Weather;

import java.util.List;

public interface InfluxDBService {
    void insertRows(List<Row> rows,String cityid);

    void insertRow();

    void insertAqi(Aqi aqi,String cityid);

    void insertWeather(Weather weather);
}
