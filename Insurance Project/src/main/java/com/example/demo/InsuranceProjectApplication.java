package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories //I am activating JPA in my ICP project
public class InsuranceProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceProjectApplication.class, args);
    }

}

//todo: DateTimeFormater utility třída - DRY??
//todo: formatovat data na stránce My events + Insurance detail

//todo: možnost změnit uživatelské heslo
//todo: po registraci zustan rovnou přihlášený?
//todo: smazat muj vlastní profil
//todo: přihlasit rovvnou po registraci

//todo: chci řadit eventy a insurace dle svého id

//todo: css - aby byli option in create insurance - zelený a ne modrý při výběru
//todo: možnost schovat nav bar?

//todo: nápovědy k formulářům
//todo: předělat deleteInsurance z Get metody na Delete Metodu!
