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
//todo tohle musim přejmenovat, celá složka působí divně

@Controller
public class pages {

    @Autowired
    private UserService userService;


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

    public String showRegisterPage(@ModelAttribute UserDTO userDTO){
        return "register-Page";
    }
    @GetMapping("/registrationSuccess") //prozatimní řešení, potvrzení že registrace proběhla úspěšně
    public  String showRegistrationSuccess(){
        return "registrationSuccess-Page";
    }

    @PostMapping("/register")
    public String handleRegisterPage(@Valid @ModelAttribute UserDTO userDTO, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return showRegisterPage(userDTO);
        }

        redirectAttributes.addFlashAttribute("success", "User is registered");
        return "registrationSuccess-Page";//todo opravit navratovou hodnotu
    }

    public String registerPage(){
        return "register-Page";
    }

}
