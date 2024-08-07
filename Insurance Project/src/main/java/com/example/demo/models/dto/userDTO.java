package com.example.demo.models.dto;

public class userDTO {
    // @NotBlank(message = "Vyplňte uživatelské jméno")
    private String userName; //uživatelské jméno

    // @Email(message = "Vyplňte validní email")
    // @NotBlank(message = "Vyplňte uživatelská email")
    private String email;// uživatelský email

    // @NotBlank(message = "Vyplňte své křestní jméno")
    private String firstName; //Křestní jméno užvatele todo: je nutné mít to už zde? nelze mit oddeělenou entitu na profilu

    // @NotBlank(message = "Vyplňte své příjmení")
    private String lastName; //Příjmení uživatele todo: to samé jako u křestního jména

    // @NotBlank(message = "Vyplňte uživatelské heslo")
    // @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
    private String password;

    // @NotBlank(message = "Vyplňte uživatelské heslo")
    // @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
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
