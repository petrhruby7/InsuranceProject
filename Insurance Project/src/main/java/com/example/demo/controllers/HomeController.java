package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String landingPage(){
        return "landing-Page"; //vrací šablonu landing page, úvodní stránka pro nepřihlášené
    }

    @GetMapping("/home")
    public String homePage(){
        return "home-Page"; //vrací šablonu home page, pouze pro přihlášené
    }
}
