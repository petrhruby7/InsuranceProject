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
        user.setUserName(user.getUserName());
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());

        // zatím nehashuji. todo: musím napravit
        user.setPassword(user.getPassword());

        userRepository.save(user);
    }



}
