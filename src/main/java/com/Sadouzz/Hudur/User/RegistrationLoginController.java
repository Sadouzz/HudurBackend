package com.Sadouzz.Hudur.User;

import com.Sadouzz.Hudur.Student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegistrationLoginController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public RegistrationLoginController(UserRepository userRepository, UserService userService,
                                       PasswordEncoder passwordEncoder,
                                       AuthenticationManager authenticationManager,
                                       JwtUtil jwtUtil,
                                       CustomUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            // récupère le rôle depuis ton User ou UserDetails
            User userFound = userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
            //User userFound = userRepository.findByEmail(user.getEmail());
            // exemple : user.getRole() ou userDetails.getAuthorities().toString()

            return ResponseEntity.ok(new AuthResponse(jwt, userFound.getName(), userFound.getUsername(), userFound.getRole(), userFound.getStudentId(), userFound.isFirstLogin()));

        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    // DTO pour renvoyer le token
    public class AuthResponse {
        private String token;
        private String name;
        private String username;
        private String role;
        private Long studentId;
        private boolean firstLogin;



        public AuthResponse(String token, String name, String username, String role, Long studentId, boolean firstLogin) {
            this.token = token;
            this.name = name;
            this.username = username;
            this.role = role;
            this.studentId = studentId;
            this.firstLogin = firstLogin;
        }

        public String getToken() { return token; }
        public String getUsername() { return username; }

        public String getName() {
            return name;
        }

        public String getRole() { return role; }
        public Long getStudentId() { return studentId; }

        public boolean isFirstLogin() {
            return firstLogin;
        }
    }


}
