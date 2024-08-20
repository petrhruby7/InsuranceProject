package com.example.demo.models.services;

import com.example.demo.data.entities.UserEntity;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.exceptions.DuplicateEmailException;
import com.example.demo.models.exceptions.DuplicateUserNameException;
import com.example.demo.models.exceptions.PasswordDoNotEqualException;
import com.example.demo.models.exceptions.UserIsNotAdultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.LocalDate;

@Service
public class  UserServiceImpl implements UserService {

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

        //kontrola zda je registrovaný starší 18let
        if (Period.between(userDTO.getDateOfBirth(), LocalDate.now()).getYears() < 18){
            throw new UserIsNotAdultException();
        }

        //nastavení parametrů nového usera, včetně hashování hesla
        UserEntity user = new UserEntity();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));//heslo se hashuje
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setZipCode(userDTO.getZipCode());
        user.setCountry(userDTO.getCountry());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setSocialSecurityNumber(userDTO.getSocialSecurityNumber());

        userRepository.save(user);

    }

    // nejsem si jistej zda toto funguje jak má respektive zda ta exception neni zbytečná todo zjistit
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));
    }
}
