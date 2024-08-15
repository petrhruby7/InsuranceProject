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

    public @NotBlank(message = "Country is required") String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank(message = "Country is required") String country) {
        this.country = country;
    }

    public @NotBlank(message = "ZIP code is required") String getZipCode() {
        return zipCode;
    }

    public void setZipCode(@NotBlank(message = "ZIP code is required") String zipCode) {
        this.zipCode = zipCode;
    }

    public @NotBlank(message = "City is required") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "City is required") String city) {
        this.city = city;
    }

    public @NotBlank(message = "Address is required") String getAdrress() {
        return adrress;
    }

    public void setAdrress(@NotBlank(message = "Address is required") String adrress) {
        this.adrress = adrress;
    }

    public @NotBlank(message = "Phone Number is required") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone Number is required") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //konec regionu
}
