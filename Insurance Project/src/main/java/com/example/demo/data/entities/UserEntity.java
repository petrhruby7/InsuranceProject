package com.example.demo.data.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "Users")
public class UserEntity implements UserDetails {

    /**
     * Id uživatele: samo se generuje
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * User name:uživatelské jméno - musí být jedinečné, potřebné pro přihlášení
     * musí být vyplněno
     */
    @Column(nullable = false, unique = true)
    private String userName;

    /**
     * email: musí být jedinečný
     * musí být vyplněn
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Křestní jméno uživatele
     * musí být vyplněné
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Přijmení uživatele
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * heslo uživatele
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String password;

    /**
     * telefoní číslo uživatel
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String phoneNumber;
    /**
     * adresa uživatele
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String address;
    /**
     * město uživatele
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String city;
    /**
     * směrovací číslo uživatele - tzv. PSČ
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String zipCode;
    /**
     * Stát uživatele
     * muí být vyplněno
     */
    @Column(nullable = false)
    private String country;
    /**
     * Datum narození uživatele
     * musí být vyplněno
     */
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    /**
     * rodné číslo uživatele
     * musí být vyplěno
     */
    @Column(nullable = false)
    private String socialSecurityNumber;
    /**
     * Seznam pojištění které si může uživatel sjednat
     * Uživatel je jeden a může mít mnoho poijštění
     */
    @OneToMany
    private List<InsuranceEntity> insurances;


    // todo: boolean zda jakou má roli

    //region: UserDetails Methods

    @Override
    public String getUsername() {
        return userName;
    } //pomáhá uživateli přihlásit dle uživatelského jména

    @Override
    public String getPassword() {
        return password;
    } //pomáhá uživateli přihlásit dle uživatelského jména

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //todo implementace rolí
        return null;
    }

    //konec regionu UserDetails Methods

    // region gettery a settery

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public void setPassword(String password) {
        this.password = password;
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


    public List<InsuranceEntity> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<InsuranceEntity> insurances) {
        this.insurances = insurances;
    }


    // konec regionu gettery a settery
}
