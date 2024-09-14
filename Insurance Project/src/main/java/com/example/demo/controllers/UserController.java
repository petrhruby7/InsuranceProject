package com.example.demo.controllers;

import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.exceptions.DuplicateEmailException;
import com.example.demo.models.exceptions.DuplicateUserNameException;
import com.example.demo.models.exceptions.PasswordDoNotEqualException;
import com.example.demo.models.exceptions.UserIsNotAdultException;
import com.example.demo.models.services.UserServiceImpl;
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
    private UserServiceImpl userService;

    //render login page
    @GetMapping("/login")
    public String loginPage() {
        return "/user/login-Page";
    }

    //render register page
    @GetMapping("/register")
    public String showRegisterPage(@ModelAttribute UserDTO userDTO) {

        return "/user/register-Page";
    }

    //will allow you to create a registration
    @PostMapping("/register")
    public String handleRegisterPage(
            @Valid @ModelAttribute UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return showRegisterPage(userDTO);

        try {
            userService.createUser(userDTO);
        } catch (DuplicateUserNameException e) {
            result.rejectValue("userName", "error", "Username is already taken");
            return ("/user/register-Page"); //check if UserName is not taken
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "Email is already taken");
            return ("/user/register-Page"); //check if email is not taken
        } catch (PasswordDoNotEqualException e) {
            result.rejectValue("password", "error", "Passwords do not match. please try again");
            result.rejectValue("confirmPassword", "error", "Passwords do not match. please try again");
            return ("/user/register-Page"); //check if password and confirm password matches
        } catch (UserIsNotAdultException e) {
            result.rejectValue("dateOfBirth", "error", "User is not older than 18 years");
            return ("/user/register-Page"); //check if User is adult
        }

        redirectAttributes.addFlashAttribute("success", "User is registered");
        return "redirect:/";
    }
}
