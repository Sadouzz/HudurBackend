package com.Sadouzz.Hudur.Controller;

import com.Sadouzz.Hudur.User.Role;
import com.Sadouzz.Hudur.User.User;
import com.Sadouzz.Hudur.User.UserService;
import com.Sadouzz.Hudur.dto.LoginRequest;
import com.Sadouzz.Hudur.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        try {
            logger.info("🔐 Tentative de login pour: {}", request.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Spring gère le SecurityContext dans la session automatiquement
            HttpSession session = httpRequest.getSession(true);
            logger.info("📝 Session créée/récupérée: {}", session.getId());

            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "username", request.getUsername(),
                    "sessionId", session.getId()
            ));
        } catch (Exception e) {
            logger.error("❌ Erreur de login pour {}: {}", request.getUsername(), e.getMessage());
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User newUser = userService.createUser(request.getUsername(), request.getPassword(), Role.USER);
            logger.info("✅ Utilisateur créé: {}", newUser.getUsername());
            return ResponseEntity.ok(Map.of(
                    "message", "User registered successfully",
                    "id", newUser.getId(),
                    "username", newUser.getUsername()
            ));
        } catch (RuntimeException e) {
            logger.error("❌ Erreur de registration: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            logger.info("🚪 Destruction de la session: {}", session.getId());
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        logger.info("🔍 Check auth - Session: {}, Auth: {}, Principal: {}",
                session != null ? session.getId() : "null",
                auth != null ? auth.getName() : "null",
                auth != null ? auth.getPrincipal().getClass().getSimpleName() : "null");

        if (session == null || auth == null || !auth.isAuthenticated() ||
                "anonymousUser".equals(auth.getName()) ||
                auth.getPrincipal().toString().equals("anonymousUser")) {
            logger.warn("⚠️ Non authentifié ou utilisateur anonyme");
            return ResponseEntity.status(401).body(Map.of("authenticated", false));
        }

        logger.info("✅ Authentifié: {}", auth.getName());
        return ResponseEntity.ok(Map.of(
                "authenticated", true,
                "user", auth.getName(),
                "sessionId", session.getId(),
                "authorities", auth.getAuthorities()
        ));
    }
}
