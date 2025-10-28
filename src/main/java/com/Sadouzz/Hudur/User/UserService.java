package com.Sadouzz.Hudur.User;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> createUser(User user) {
        String tempPassword = RandomStringUtils.randomAlphanumeric(10);

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }

        user.setPassword(passwordEncoder.encode(tempPassword));
        userRepository.save(user);

        emailService.sendEmail(
                user.getEmail(),
                "Votre mot de passe temporaire",
                "Bonjour,\n\nVotre mot de passe temporaire est : " + tempPassword +
                        "\n\nVeuillez le changer dès votre première connexion."
        );

        return ResponseEntity.ok(user);
    }
}
