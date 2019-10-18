package com.hexin.sample.ScheduleTask;

import com.hexin.sample.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class WeatherTask {

    @Autowired
    WeatherService weatherService;

    //3.添加定时任务
    //@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：20分钟
    @Scheduled(fixedRate = 1000*60*20)
    private void Point_Aqi_Tasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        weatherService.insertData();
    }


//    @Scheduled(fixedRate = 1000*60*60*24*30)
//    private void City_Point_Tasks() {
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//        weatherService.insertData();
//    }


}
