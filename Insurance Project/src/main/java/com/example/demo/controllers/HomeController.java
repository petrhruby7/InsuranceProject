package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //render landing page
    @GetMapping("/")
    public String landingPage() {
        return "landing-Page";
    }

    //render home page
    @GetMapping("/home")
    public String homePage() {
        return "home-Page";
    }

    //render about us Page
    @GetMapping("/aboutUs")
    public String aboutUsPage() {
        return "aboutUs-Page";
    }
}
