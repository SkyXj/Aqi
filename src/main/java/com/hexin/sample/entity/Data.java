package com.hexin.sample.entity;

import java.util.List;
@lombok.Data
public class Data {
    private Cityinfo cityinfo;
    private Aqi aqi;
    private List<Row> rows;
    private int total;
    private Weather weather;
}
