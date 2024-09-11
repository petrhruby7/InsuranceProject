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

//todo: DateTimeFormater utility třída - DRY
//todo: formatovat data na stránce My events + Insurance detail

//todo: možnost změnit uživatelské heslo
//todo: po registraci zustan rovnou přihlášený?
//todo: smazat muj vlastní profil

//todo: chci řadit eventy a insurace dle svého id

//todo: css - aby byli option in create insurance - zelený a ne modrý při výběru