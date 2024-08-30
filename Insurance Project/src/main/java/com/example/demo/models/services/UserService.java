package com.example.demo.models.services;

import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.dto.UserProfileDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    //metoda vytvoří uživatele
    void createUser(UserDTO user);

    //metoda umožní upravit uživatelovi údaje
    void updateUserProfile(UserProfileDTO userProfileDTO);


}
