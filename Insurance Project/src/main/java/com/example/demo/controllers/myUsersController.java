package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class myUsersController {

    // render My User Page
    @GetMapping("/myUsers")
    public String renderMyUsersPage() {
        return "/my users/myUsers-Page";
    }
}
