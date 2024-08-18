package com.example.demo.controllers;

import com.example.demo.models.services.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    ProfileServiceImpl profileService;

    @GetMapping ("/profile")
    public String profilePage(){
        return "/profile/profile-Page";
    }
}
