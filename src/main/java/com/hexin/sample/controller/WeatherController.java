/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: WeatherController.java 
 * @Package: com.hexin.sample.web.api
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-09-24 19:05:59
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.hexin.sample.controller;

import com.hexin.sample.entity.Row;
import com.hexin.sample.entity.Weather;
import com.hexin.sample.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController{

	@Autowired
	WeatherService weatherService;

	@GetMapping("/test")
	public String test(){
		//获得当前时间
		long startTime=System.currentTimeMillis();
		weatherService.insertData();
		//获得当前时间
		long endTime=System.currentTimeMillis();
		String time=((endTime-startTime)/1000)+"s";
		System.out.println(time);
		return time;
	}

	@GetMapping("/getTest")
	public List<Weather> getTest(){
		return weatherService.getTest();
	}

	@GetMapping("/testbycityname")
	public String testbycityname(@RequestParam(value="cityname") String cityname){
		//获得当前时间
		long startTime=System.currentTimeMillis();
		weatherService.insertByCity(cityname);
		//获得当前时间
		long endTime=System.currentTimeMillis();
		String time=((endTime-startTime)/1000)+"s";
		System.out.println(time);
		return time;
	}

	@GetMapping("/getAqiByCityName")
	public List<Row> getAqiByCityName(@RequestParam(value="cityname") String cityname){
		//获得当前时间
		List<Row> rows = weatherService.getRowByCityName(cityname);
		return rows;
	}
}