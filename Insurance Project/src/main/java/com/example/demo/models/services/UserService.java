package com.example.demo.models.services;

import com.example.demo.models.dto.UserDTO;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {




public interface UserService {


    void createUser (UserDTO user);
}
