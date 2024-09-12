package com.example.demo.models.services;

import com.example.demo.data.entities.UserEntity;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.dto.UserProfileDTO;
import com.example.demo.models.exceptions.DuplicateEmailException;
import com.example.demo.models.exceptions.DuplicateUserNameException;
import com.example.demo.models.exceptions.PasswordDoNotEqualException;
import com.example.demo.models.exceptions.UserIsNotAdultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new user
     *
     * @param userDTO User data transfer object containing user details.
     */
    @Override
    public void createUser(UserDTO userDTO) {
        //check if password and confirm password matches
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword()))
            throw new PasswordDoNotEqualException();

        //check if UserName is not taken
        if (userRepository.findByUserName(userDTO.getUserName()).isPresent()) {
            throw new DuplicateUserNameException();
        }
        //check if email is not taken
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new DuplicateEmailException();
        }
        //check if User is adult
        validateUserAge(userDTO.getDateOfBirth());

        //setting parameters of the new user, including password hashing
        UserEntity user = new UserEntity();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setZipCode(userDTO.getZipCode());
        user.setCountry(userDTO.getCountry());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setSocialSecurityNumber(userDTO.getSocialSecurityNumber());

        userRepository.save(user);
    }

    /**
     * Updates an existing user with new data and checks if the date is valid.
     *
     * @param userProfileDTO Updated user data transfer object.
     */
    @Override
    public void updateUserProfile(UserProfileDTO userProfileDTO) {
        //finds the currently logged-in user
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUserName(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //check if User is adult
        validateUserAge(userProfileDTO.getDateOfBirth());

        //updates the user's data
        user.setEmail(userProfileDTO.getEmail());
        user.setFirstName(userProfileDTO.getFirstName());
        user.setLastName(userProfileDTO.getLastName());
        user.setPhoneNumber(userProfileDTO.getPhoneNumber());
        user.setAddress(userProfileDTO.getAddress());
        user.setCity(userProfileDTO.getCity());
        user.setZipCode(userProfileDTO.getZipCode());
        user.setCountry(userProfileDTO.getCountry());
        user.setDateOfBirth(userProfileDTO.getDateOfBirth());
        user.setSocialSecurityNumber(userProfileDTO.getSocialSecurityNumber());

        userRepository.save(user);
    }

    /**
     * Loads a user by their username for authentication.
     *
     * @param username Username of the user to be loaded.
     * @return UserDetails object for the specified username.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));
    }
    /**
     * Retrieves the current user from the security context.
     *
     * @return UserDTO object for the current user.
     * @throws UsernameNotFoundException if the user is not found.
     */
    public UserDTO getCurrentUser() {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUserName(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return toDTO(user);
    }


    /**
     * Converts a UserEntity to UserDTO.
     *
     * @param user UserEntity object to be converted.
     * @return     UserDTO object with user details.
     */
    private UserDTO toDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setCity(user.getCity());
        userDTO.setZipCode(user.getZipCode());
        userDTO.setCountry(user.getCountry());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setSocialSecurityNumber(user.getSocialSecurityNumber());
        return userDTO;
    }

    /**
     * Validates that the user's age is 18 or older.
     *
     * @param dateOfBirth The date of birth of the user.
     * @throws UserIsNotAdultException if the user is under 18 years old.
     */
    private void validateUserAge(LocalDate dateOfBirth) {
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
            throw new UserIsNotAdultException();
        }
    }


}
