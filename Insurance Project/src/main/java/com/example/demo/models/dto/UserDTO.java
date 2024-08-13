package com.example.demo.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotBlank(message = "User name is required")
    private String userName; //uživatelské jméno

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;// uživatelský email

    @NotBlank(message = "First name is required")
    private String firstName; //Křestní jméno užvatele todo: je nutné mít to už zde? nelze mit oddeělenou entitu na profilu

    @NotBlank(message = "Last name is required")
    private String lastName; //Příjmení uživatele todo: to samé jako u křestního jména

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String password;

    @NotBlank(message = "Confirm password is required")
    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String confirmPassword;

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
