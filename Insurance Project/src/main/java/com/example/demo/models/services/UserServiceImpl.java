package com.example.demo.models.services;

import com.example.demo.data.entities.UserEntity;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.models.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser (UserDTO userDTO){

        //pozdějí vyřeším pomocí mapperu
        UserEntity user = new UserEntity();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        // zatím nehashuji. todo: musím napravit
        user.setPassword(userDTO.getPassword());

        userRepository.save(user);
    }



}
