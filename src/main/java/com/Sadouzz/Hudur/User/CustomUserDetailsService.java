package com.Sadouzz.Hudur.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Optional<User> userEmail = userRepository.findByEmail(email);
        Optional<User> userUsername = userRepository.findByUsername(email);

        if (userUsername.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email or username: " + email);
        }
        User userFound = userUsername.get();

        return new org.springframework.security.core.userdetails.User(userFound.getUsername(),
                userFound.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(userFound.getRole())));
    }
}