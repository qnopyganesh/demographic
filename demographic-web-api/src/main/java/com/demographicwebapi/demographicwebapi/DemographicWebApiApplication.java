package com.demographicwebapi.demographicwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class DemographicWebApiApplication {

	public static void main(String[] args) {	
		SpringApplication.run(DemographicWebApiApplication.class, args);
	}

}
