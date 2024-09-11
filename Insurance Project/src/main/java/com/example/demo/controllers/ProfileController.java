package com.example.demo.controllers;

import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.dto.UserProfileDTO;
import com.example.demo.models.exceptions.DuplicateEmailException;
import com.example.demo.models.exceptions.UserIsNotAdultException;
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

import java.time.format.DateTimeFormatter;


@Controller
public class ProfileController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/profile")
    public String showProfilePage(Model model){
        UserDTO userDTO = userService.getCurrentUser();
        model.addAttribute("userDTO", userDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfBirthFormatter = userDTO.getDateOfBirth().format(formatter);
        model.addAttribute("formatterDateOfBirth", dateOfBirthFormatter);

        return "profile/profile-Page"; //vrací na profile-Page
    }

    //zobrazení formuláře (uprava osobních údajů)
    @GetMapping("/profile/update")
    public String showUpdateProfilePage(Model model){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        UserDTO currentUser = userService.getCurrentUser(); //získání přihlášeného uživatele

        //mapování udajů
        userProfileDTO.setEmail(currentUser.getEmail());
        userProfileDTO.setFirstName(currentUser.getFirstName());
        userProfileDTO.setLastName(currentUser.getLastName());
        userProfileDTO.setPhoneNumber(currentUser.getPhoneNumber());
        userProfileDTO.setAddress(currentUser.getAddress());
        userProfileDTO.setCity(currentUser.getCity());
        userProfileDTO.setZipCode(currentUser.getZipCode());
        userProfileDTO.setCountry(currentUser.getCountry());
        userProfileDTO.setDateOfBirth(currentUser.getDateOfBirth());
        userProfileDTO.setSocialSecurityNumber(currentUser.getSocialSecurityNumber());

        model.addAttribute("userProfileDTO", userProfileDTO);
        return "profile/updateProfile-Page"; //vrátí updateProfilePage
    }

    //možnost zadat nové údaje do formuláře pro úpravu osobních údajů
    @PostMapping("/profile/update")
    public String handleUpdateProfile(
            @Valid @ModelAttribute("userProfileDTO")UserProfileDTO userProfileDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ){
        if (result.hasErrors()) {
            model.addAttribute("userProfileDTO", userProfileDTO);
            return "profile/updateProfile-Page"; //pokud se nepodaří změnit údaje, vrátí na updateProfile-Page
        }

        try {
            userService.updateUserProfile(userProfileDTO);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "Email is already taken");
            return ("/profile/updateProfile-Page"); //kontrola zda email není zabrán
        } catch (UserIsNotAdultException e) {
            result.rejectValue("dateOfBirth","error", "User is not older than 18 years");
            return ("/profile/updateProfile-Page"); //kontrola že je uživatel dospěl
        }

        redirectAttributes.addFlashAttribute("success","Your changes have been saved");
        return "redirect:/profile"; // po úspěšné změně, přesměruje zpět na profil.Page.
    }

}
