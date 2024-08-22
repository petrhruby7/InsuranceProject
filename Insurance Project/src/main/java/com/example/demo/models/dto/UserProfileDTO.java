package com.example.demo.models.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserProfileDTO {

    //DTO pro update údajů které chci měnit aniž bych byl nucen měnit heslo

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;// uživatelský email - musí být zadán ve formátu emailu

    @NotBlank(message = "First name is required")
    private String firstName; //Křestní jméno užvatele  - musí být zadáno

    @NotBlank(message = "Last name is required")
    private String lastName; //Příjmení uživatele  - musí být zadáno

    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber; //telefonní číslo uživatele - musí být zadáno

    @NotBlank(message = "Address is required")
    private String address; //addresa uživatele  - musí být zadána

    @NotBlank(message = "City is required")
    private String city; // město uživatele - musí být zadáno

    @NotBlank(message = "ZIP/Postal code is required")
    @Pattern(regexp = "\\d{5}", message = "Your ZIP/Postal code must contain 5 digits")
    private String zipCode; //Psč uživatele - musí být zadáno (5 čísel)

    @NotBlank(message = "Country is required")
    private String country; //stát uživatele  - musí být zadáno

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in past")
    private LocalDate dateOfBirth; //datum narození uživatele - musí být zadáno (musí být v minulosti)

    @NotBlank(message = "social security number (the birth number) is required")
    @Pattern(regexp = "\\d{6}/\\d{4}", message = "social security number (the birth number) must be in format ######/####")
    private String socialSecurityNumber; //rodné číslo uživatele - musí být zadáno (######/####)

    //region getters a setters

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
    //konec getters a setters
}


