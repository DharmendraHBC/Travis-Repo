package com.hbc.integration.loyality.api.partners_points_award;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PartnersPointsAwardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartnersPointsAwardApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
