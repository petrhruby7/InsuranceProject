package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class myUsersController {


    @GetMapping("/myUsers")
    public String renderMyUsersPage(){
        return "/my users/myUsers-Page";
    }
}
