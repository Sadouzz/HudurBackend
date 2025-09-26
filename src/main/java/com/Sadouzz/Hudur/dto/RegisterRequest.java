package com.Sadouzz.Hudur.dto;

public class RegisterRequest {
    private String username;
    private String password;
    private String email; // optionnel

    // Constructeur par défaut
    public RegisterRequest() {}

    // Constructeur avec paramètres
    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters et Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}