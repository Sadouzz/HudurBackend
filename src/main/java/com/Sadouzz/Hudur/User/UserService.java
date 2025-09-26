package com.Sadouzz.Hudur.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(String username, String password, Role role) {
        System.out.println("🆕 Création utilisateur: " + username); // Debug

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("🔐 Password encodé: " + encodedPassword.substring(0, 10) + "..."); // Debug

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setRole(role);

        User savedUser = userRepository.save(user);
        System.out.println("✅ Utilisateur sauvé avec ID: " + savedUser.getId()); // Debug

        return savedUser;
    }

    // Méthode utilitaire pour tester l'authentification
    public boolean verifyPassword(String username, String rawPassword) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            System.out.println("❌ Utilisateur non trouvé pour vérification: " + username);
            return false;
        }

        boolean matches = passwordEncoder.matches(rawPassword, user.getPassword());
        System.out.println("🔍 Vérification password pour " + username + ": " + matches);
        return matches;
    }
}