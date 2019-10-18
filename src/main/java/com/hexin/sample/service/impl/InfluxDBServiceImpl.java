package com.hexin.sample.service.impl;

import com.hexin.sample.tool.InfluxDbUtils;
import com.hexin.sample.entity.Aqi;
import com.hexin.sample.entity.Row;
import com.hexin.sample.entity.Weather;
import com.hexin.sample.service.InfluxDBService;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class InfluxDBServiceImpl implements InfluxDBService {
    @Value("${spring.influx.database}")
    String dataDaseName;

    @Autowired
    InfluxDbUtils influxDbUtils;

    @Override
    public void insertRows(List<Row> rows,String cityid) {
        BatchPoints batchPoints = BatchPoints.database(dataDaseName).consistency(InfluxDB.ConsistencyLevel.ALL).build();
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        String tablename="aqi_point";
        int i = 0;
        for (Row row : rows) {
            //System.out.println(row.getPointname());
            //Aqi
            long datatime=getUtcTime(row.getTime());
            Point point = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "aqi")
                    .addField("value", row.getAqi() == null ? "" : row.getAqi()).build();
            //pm_25
            Point point_pm25 = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "pm_25")
                    .addField("value", row.getPm2_5() == null ? "" : row.getPm2_5()).build();
            //pm10
            Point point_pm10 = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "pm_10")
                    .addField("value", row.getPm10() == null ? "" : row.getPm10()).build();
            //so2
            Point point_so2 = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "so2")
                    .addField("value", row.getSo2() == null ? "" : row.getSo2()).build();
            //no2
            Point point_no2 = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "no2")
                    .addField("value", row.getNo2() == null ? "" : row.getNo2()).build();
            //co
            Point point_co = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "co")
                    .addField("value", row.getCo() == null ? "" : row.getCo()).build();
            //o3
            Point point_o3 = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "03")
                    .addField("value", row.getO3() == null ? "" : row.getO3()).build();
            //complexindex
            Point point_complexindex = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "complexindex")
                    .addField("value", row.getComplexindex() == null ? "" : row.getComplexindex()).build();
            //ratio
            Point point_ratio = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "ratio")
                    .addField("value", row.getRatio() == null ? "" : row.getRatio()).build();
            //indexratio
            Point point_indexratio = Point.measurement(tablename)//表名
                    .time(datatime, TimeUnit.MILLISECONDS)
                    //.tag("utime", row.getTime() == null ? "" : row.getTime())
                    .tag("cityid",cityid)
                    .tag("pointgid",row.getPointgid()==null?"":row.getPointgid())
                    .tag("pointname",row.getPointname()==null?"":row.getPointname())
                    .tag("yz", "indexratio")
                    .addField("value", row.getIndexratio() == null ? "" : row.getIndexratio()).build();
            batchPoints.point(point);
            batchPoints.point(point_pm25);
            batchPoints.point(point_pm10);
            batchPoints.point(point_so2);
            batchPoints.point(point_no2);
            batchPoints.point(point_co);
            batchPoints.point(point_o3);
            batchPoints.point(point_complexindex);
            batchPoints.point(point_ratio);
            batchPoints.point(point_indexratio);
        }
        influxDB.write(batchPoints);
    }

    @Override
    public void insertRow() {
        BatchPoints batchPoints = BatchPoints.database(dataDaseName).consistency(InfluxDB.ConsistencyLevel.ALL).build();
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        int i = 0;
        //表名
        Point point = Point.measurement("area")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("selftime", "123")
                .addField("aqi", "90")
                .build();
        batchPoints.point(point);
        influxDB.write(batchPoints);
    }

    @Override
    public void insertAqi(Aqi aqi,String cityid) {
        long datatime=getUtcTime(aqi.getTime());
        BatchPoints batchPoints = BatchPoints.database(dataDaseName).consistency(InfluxDB.ConsistencyLevel.ALL).build();
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        int i = 0;
        String tablename="aqi_city";
        //Aqi
        Point point = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "aqi")
                .addField("value", aqi.getAqi() == null ? "" : aqi.getAqi()).build();
        //pm_25
        Point point_pm25 = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "pm_25")
                .addField("value", aqi.getPm2_5() == null ? "" : aqi.getPm2_5()).build();
        //pm10
        Point point_pm10 = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "pm_10")
                .addField("value", aqi.getPm10() == null ? "" : aqi.getPm10()).build();
        //so2
        Point point_so2 = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "so2")
                .addField("value", aqi.getSo2() == null ? "" : aqi.getSo2()).build();
        //no2
        Point point_no2 = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "no2")
                .addField("value", aqi.getNo2() == null ? "" : aqi.getNo2()).build();
        //co
        Point point_co = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "co")
                .addField("value", aqi.getCo() == null ? "" : aqi.getCo()).build();
        //o3
        Point point_o3 = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "03")
                .addField("value", aqi.getO3() == null ? "" : aqi.getO3()).build();
        //day_aqi
        Point point_day_aqi = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "day_aqi")
                .addField("value", aqi.getDay_aqi() == null ? "" : aqi.getDay_aqi()).build();
        //day_complex
        Point point_day_complex = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "day_complex")
                .addField("value", aqi.getDay_complex() == null ? "" : aqi.getDay_complex()).build();
        //complexrank
        Point point_complexrank = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid",cityid)
                .tag("city", aqi.getCity() == null ? "" : aqi.getCity())
                .tag("yz", "complexrank")
                .addField("value", aqi.getComplexrank() == null ? "" : aqi.getComplexrank()).build();
        batchPoints.point(point);
        batchPoints.point(point_pm25);
        batchPoints.point(point_pm10);
        batchPoints.point(point_so2);
        batchPoints.point(point_no2);
        batchPoints.point(point_co);
        batchPoints.point(point_o3);
        batchPoints.point(point_day_aqi);
        batchPoints.point(point_day_complex);
        batchPoints.point(point_complexrank);
        influxDB.write(batchPoints);
    }

    @Override
    public void insertWeather(Weather weather) {
        long datatime=getUtcTime(weather.getTime());
        BatchPoints batchPoints = BatchPoints.database(dataDaseName).consistency(InfluxDB.ConsistencyLevel.ALL).build();
        InfluxDB influxDB = influxDbUtils.getInfluxDB();
        int i = 0;
        String tablename="weather";
        //weather
        Point point_weather = Point.measurement(tablename)//表名
                .time(datatime, TimeUnit.MILLISECONDS)
                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
                .addField("weather", weather.getWeather() == null ? "" : weather.getWeather())
                .addField("temp", weather.getTemp() == null ? "" : weather.getTemp())
                .addField("humi", weather.getHumi() == null ? "" : weather.getHumi())
                .addField("rain", weather.getRain() == null ? "" : weather.getRain())
                .addField("wd", weather.getWd() == null ? "" : weather.getWd())
                .addField("wdangle", weather.getWdangle() == null ? "" : weather.getWdangle())
                .addField("ws", weather.getWs() == null ? "" : weather.getWs())
                .addField("wl", weather.getWl() == null ? "" : weather.getWl())
                .addField("visibility", weather.getVisibility() == null ? "" : weather.getVisibility())
                .addField("pressure", weather.getPressure() == null ? "" : weather.getPressure())
                .addField("tq", weather.getTq() == null ? "" : weather.getTq())
                .build();
//                .addField("value", weather.getWeather() == null ? "" : weather.getWeather()).build();
//        //temp
//        Point point_temp = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "temp")
//                .addField("value", weather.getTemp() == null ? "" : weather.getTemp()).build();
//        //humi
//        Point point_humi = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "humi")
//                .addField("value", weather.getHumi() == null ? "" : weather.getHumi()).build();
//        //rain
//        Point point_rain = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "rain")
//                .addField("value", weather.getRain() == null ? "" : weather.getRain()).build();
//        //wd
//        Point point_wd = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "wd")
//                .addField("value", weather.getWd() == null ? "" : weather.getWd()).build();
//        //wdangle
//        Point point_wdangle = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "wdangle")
//                .addField("value", weather.getWdangle() == null ? "" : weather.getWdangle()).build();
//        //ws
//        Point point_ws = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "ws")
//                .addField("value", weather.getWs() == null ? "" : weather.getWs()).build();
//        //wl
//        Point point_wl = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "wl")
//                .addField("value", weather.getWl() == null ? "" : weather.getWl()).build();
//        //visibility
//        Point point_visibility = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "visibility")
//                .addField("value", weather.getVisibility() == null ? "" : weather.getVisibility()).build();
//        //pressure
//        Point point_pressure = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "pressure")
//                .addField("value", weather.getPressure() == null ? "" : weather.getPressure()).build();
//        //tq
//        Point point_tq = Point.measurement(tablename)//表名
//                .time(datatime, TimeUnit.MILLISECONDS)
//                //.tag("utime", aqi.getTime() == null ? "" : aqi.getTime())
//                .tag("cityid", weather.getCityid() == null ? "" : weather.getCityid())
//                .tag("yz", "tq")
//                .addField("value", weather.getTq() == null ? "" : weather.getTq()).build();
        batchPoints.point(point_weather);
//        batchPoints.point(point_temp);
//        batchPoints.point(point_humi);
//        batchPoints.point(point_rain);
//        batchPoints.point(point_wd);
//        batchPoints.point(point_wdangle);
//        batchPoints.point(point_ws);
//        batchPoints.point(point_wl);
//        batchPoints.point(point_visibility);
//        batchPoints.point(point_pressure);
//        batchPoints.point(point_tq);
        influxDB.write(batchPoints);
    }

    private long getUtcTime(String datatime){
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //由于时序数据库 用的时区不一致 需要加八个小时
            long time = dateformat.parse(datatime).getTime()+8*3600000;
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
