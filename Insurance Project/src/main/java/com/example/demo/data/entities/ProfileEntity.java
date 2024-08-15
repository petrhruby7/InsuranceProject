package com.example.demo.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Profiles")
public class ProfileEntity {

    /**
     * Id profilu: samo s vygeneruje
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    /**
    firstName, lastName a email jsou v jiné tabulce todo vyřešit
     */

    /**
     * telefoní číslo profilu
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String phoneNumber;

    /**
     * Adressa profilu
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String address;

    /**
     * Město profilu
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String city;

    /**
     * směrovací číslo
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String zipCode;
    /**
     * stát
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String country;

    //region gettery a settery
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
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

    //konec regionu
}
