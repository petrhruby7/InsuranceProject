package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories // Aktivuji JPA ve svém ICP projektu

public class InsuranceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceProjectApplication.class, args);
	}


}

//todo: nezapomenout na atributy v register a login thymeleaf
//todo: sehnat dependency pro mapper a pro validationAPI


