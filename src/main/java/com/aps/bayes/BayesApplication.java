package com.aps.bayes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@CrossOrigin(origins = "*")
public class BayesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BayesApplication.class, args);
	}
}
