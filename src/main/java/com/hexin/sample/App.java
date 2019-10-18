package com.hexin.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.hexin.sample.*"})
@MapperScan("com.hexin.sample.mapper")
@RestController
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
