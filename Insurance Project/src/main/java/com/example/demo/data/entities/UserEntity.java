package com.example.demo.data.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Entity
@Table(name = "Users")
public class UserEntity implements UserDetails {

    /**
    Id uživatele: samo se generuje
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

    // todo: boolean zda jakou má roli

    //region: UserDetails Methods

    @Override
    public String getUsername(){ return userName;} //pomáhá uživateli přihlásit dle uživatelského jména

    @Override
    public String getPassword(){ return password;} //pomáhá uživateli přihlásit dle uživatelského jména

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

    // konec regionu gettery a settery
}
