package com.example.demo.models.services;

import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.dto.UserProfileDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    //method for creating user
    void createUser(UserDTO user);

    //method for editing user's personal information
    void updateUserProfile(UserProfileDTO userProfileDTO);


}
