package com.example.demo.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String landingPage(){
        return "landing-Page";
    }

    @GetMapping("/home")

    public String homePage(){
        return "home-Page";
    }
}
