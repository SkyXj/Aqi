package com.hexin.sample.entity;

import lombok.Data;

@Data
public class Row implements Comparable<Row> {
    private String time;
    private String cityname;
    private String pointgid;
    private String pointname;
    private String pointlevel;
    private String region;
    private String latitude;
    private String longitude;
    private String aqi;
    private String zq_aqi;
    private String pm2_5;
    private String pm10;
    private String so2;
    private String no2;
    private String co;
    private String o3;
    private String complexindex;
    private String level;
    private String quality;
    private String primary_pollutant;
    private String ratio;
    private String indexratio;

    @Override
    public int compareTo(Row o) {
        if(this.pointname.equals(o.getPointname())){
            return this.getTime().compareTo(o.getTime());
        }
        return this.pointname.compareTo(o.getPointname());
    }
}
