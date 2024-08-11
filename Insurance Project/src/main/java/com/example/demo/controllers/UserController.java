package com.example.demo.controllers;

import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //kontroler pro landing page todo možná do jiné složky
    @GetMapping("/")
    public String landingPage(){
        return "landing-Page";
    }
    @GetMapping("/login")
    public  String loginPage() {
        return "/user/login-Page";
    }
    @GetMapping("/register")
    public String showRegisterPage(@ModelAttribute UserDTO userDTO){
        return "/user/register-Page";
    }
    @GetMapping("/registrationSuccess") //prozatimní řešení, potvrzení že registrace proběhla úspěšně
    public  String showRegistrationSuccess(){
        return "/user/registrationSuccess-Page";
    }

    @PostMapping("/register")
    public String handleRegisterPage(
            @Valid @ModelAttribute UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes){


        if(result.hasErrors()){
            return showRegisterPage(userDTO);
        }
        userService.createUser(userDTO);
        redirectAttributes.addFlashAttribute("success", "User is registered");

        return "redirect:/";
    }

}
