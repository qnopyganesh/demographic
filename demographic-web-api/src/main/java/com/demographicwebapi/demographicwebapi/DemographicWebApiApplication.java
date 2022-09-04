package com.demographicwebapi.demographicwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemographicWebApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemographicWebApiApplication.class, args);
	}

}
