package com.Sadouzz.Hudur.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔍 Recherche utilisateur: " + username); // Debug

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("❌ Utilisateur non trouvé: " + username); // Debug
                    return new UsernameNotFoundException("User not found: " + username);
                });

        System.out.println("✅ Utilisateur trouvé: " + user.getUsername()); // Debug
        System.out.println("🔐 Password hash: " + user.getPassword().substring(0, 10) + "..."); // Debug

        UserBuilder builder = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // Le mot de passe doit déjà être hashé
                .authorities("ROLE_" + user.getRole().name());

        return builder.build();
    }
}