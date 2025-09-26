package com.Sadouzz.Hudur;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "MotDePasse123"; // mot de passe clair
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Mot de passe haché : " + hashedPassword);
    }
}
