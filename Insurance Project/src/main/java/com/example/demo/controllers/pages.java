package com.example.demo.controllers;

import com.example.demo.models.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String showRegisterPage(@ModelAttribute UserDTO userDTO){
        return "register-Page";
    }
    @PostMapping("/register")
    public String handleRegisterPage(@ModelAttribute UserDTO userDTO, BindingResult result){
        return "landing-Page";//todo opravit navratovou hodnotu
    }

}
