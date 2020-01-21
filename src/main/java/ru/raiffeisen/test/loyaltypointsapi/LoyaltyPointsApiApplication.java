package ru.raiffeisen.test.loyaltypointsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LoyaltyPointsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyPointsApiApplication.class, args);
	}

}
