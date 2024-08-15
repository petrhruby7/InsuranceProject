package com.example.demo.models.dto;

import jakarta.validation.constraints.NotBlank;

public class ProfileDTO {
    /**
     * firstName, lastName and email máme z první tabulky
     */

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber; //telefonní číslo profilu, musí být vpylněno

    @NotBlank(message = "Address is required")
    private String adrress; //adresa profilu, musí být vyplněno

    @NotBlank(message = "City is required")
    private String city; //město profilu, musí být vyplněno

    @NotBlank(message = "ZIP code is required")
    private String zipCode; //směrovací číslo profilu, musí být vyplněno

    @NotBlank(message = "Country is required")
    private String country; //Země (stát) profilu, musí být vyplněno

    //Region Gettery a Settery

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdrress() {
        return adrress;
    }

    public void setAdrress(String adrress) {
        this.adrress = adrress;
    }

    //konec regionu
}
