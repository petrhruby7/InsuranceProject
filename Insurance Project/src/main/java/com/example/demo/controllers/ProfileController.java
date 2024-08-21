package com.example.demo.controllers;

import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProfileController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/profile")
    public String showProfilePage(Model model){
        UserDTO userDTO = userService.getCurrentUser();
        model.addAttribute("userDTO", userDTO);
        return "profile/profile-Page";
    }
}
