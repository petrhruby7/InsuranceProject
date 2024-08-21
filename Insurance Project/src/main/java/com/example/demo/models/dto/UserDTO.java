package com.example.demo.models.dto;


import jakarta.validation.constraints.*;

import java.time.LocalDate;

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

    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "ZIP/Postal code is required")
    @Pattern(regexp = "\\d{5}", message = "Your ZIP/Postal code must contain 5 digits")
    private String zipCode;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "social security number (the birth number) is required")
    @Pattern(regexp = "\\d{6}/\\d{4}", message = "social security number (the birth number) must be in format ######/####")
    private String socialSecurityNumber;


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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    //konec region

}
