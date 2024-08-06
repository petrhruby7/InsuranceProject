package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pages {
    //kontroler pro landing page
    @GetMapping("/")
    public String landingPage(){
        return "landing-Page";
    }
    @GetMapping("/login")
    public  String loginPage() {
        return "login-Page";
    }
    @GetMapping("/register")
    public String registerPage(){
        return "register-Page";
    }

}
