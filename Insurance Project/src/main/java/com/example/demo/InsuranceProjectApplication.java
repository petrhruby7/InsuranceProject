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


//todo: možnost změnit uživatelské heslo
//todo: po registraci zustan rovnou přihlášený?

//todo: updateInsurance-Page - at jde zmenit pojištění i bez start date
//todo: updateInsurance-Page - at ty type-date maj vyplněnou hodnotu