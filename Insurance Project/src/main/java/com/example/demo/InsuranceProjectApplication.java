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
//todo: smazat muj vlastní profil

//todo: updateInsurance-Page - at jde zmenit pojištění i bez start date
//todo: updateInsurance-Page - at ty type-date maj vyplněnou hodnotu
//todo: zobrazit jen insurance přihlšeneho uživatele
//todo: zobrazit jen eventy přihlášeného uživatele

//todo: chci řadit eventy a insurace dle svého id

//todo: login page - bez chybných hlášek? :O
//todo: css - aby byli option in create insurance - zelený a ne modrý při výběru