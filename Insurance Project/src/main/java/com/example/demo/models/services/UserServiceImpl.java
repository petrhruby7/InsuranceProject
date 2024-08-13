package com.example.demo.models.services;

import com.example.demo.data.entities.UserEntity;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.exceptions.DuplicateEmailException;
import com.example.demo.models.exceptions.DuplicateUserNameException;
import com.example.demo.models.exceptions.PasswordDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDTO userDTO) {
        //kontrola správnosti hesla
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword()))
            throw new PasswordDoNotEqualException();

        //kontrola zda existuje uživatelské jméno
        if (userRepository.findByUserName(userDTO.getUserName()).isPresent()){
            throw new DuplicateUserNameException();
        }
        //kontrola zda již existuje email
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new DuplicateEmailException();
        }

        //pozdějí vyřeším pomocí mapperu
        UserEntity user = new UserEntity();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);

    }

    // nelze se přihlásit se špatným uživatelským jménem,či uživatelské jméno neexistuje TODO otestovat
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));
    }

}
