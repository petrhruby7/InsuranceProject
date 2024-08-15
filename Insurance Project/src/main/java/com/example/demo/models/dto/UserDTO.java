package com.example.demo.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "User name is required")
    private String userName; //uživatelské jméno - musí být zadáno

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;// uživatelský email - musí být zadán ve formátu emailu

    @NotBlank(message = "First name is required")
    private String firstName; //Křestní jméno užvatele  - musí být zadáno todo: je nutné mít to už zde? nelze mit oddeělenou entitu na profilu

    @NotBlank(message = "Last name is required")
    private String lastName; //Příjmení uživatele  - musí být zadáno todo: to samé jako u křestního jména

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String password; //Heslo uživatele  - musí být zadáno a být alespoň 6 znaků dlouhé

    @NotBlank(message = "Confirm password is required")
    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String confirmPassword; //Potvrzení heslo -  - musí být zadáno a musí se shodovat s heslem

    //region: Gettery a Settery

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    //konec region

}
