package com.example.demo.models.services;

import com.example.demo.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    //metoda vytvoří uživatele
    void createUser (UserDTO user);
    void updateUserProfile (UserDTO user);


}
