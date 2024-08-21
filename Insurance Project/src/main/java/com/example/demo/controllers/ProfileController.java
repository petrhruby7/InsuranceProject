package com.example.demo.controllers;

import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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

    @GetMapping("/profile/update")
    public String showUpdateProfilePage(Model model){
        UserDTO userDTO = userService.getCurrentUser();
        model.addAttribute("userDTO", userDTO);
        return "profile/updateProfile-Page";
    }

    @PostMapping("/profile/update")
    public String handleUpdateProfile(
            @Valid @ModelAttribute("userDTO") UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ){
        //if (result.hasErrors())
        //    return showUpdateProfilePage(userDTO);

        userService.updateUserProfile(userDTO);

        redirectAttributes.addFlashAttribute("succes","Your changes have been saved");
        return "redirect:/profile";
    }

}
