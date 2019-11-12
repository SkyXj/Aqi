package com.hexin.sample;

import com.hexin.sample.entity.Cityinfo;
import com.hexin.sample.service.CityinfoService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.hexin.sample.*"})
@MapperScan("com.hexin.sample.mapper")
@RestController
public class App {

	@Autowired
	CityinfoService cityinfoService;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@GetMapping("/test")
	public List<Cityinfo> selectAll(){
		return cityinfoService.selectAll();
	}
}
