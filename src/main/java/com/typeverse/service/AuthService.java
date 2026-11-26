package com.typeverse.service;

import com.typeverse.dto.AuthResponse;
import com.typeverse.dto.LoginRequest;
import com.typeverse.dto.RegisterRequest;
import com.typeverse.entity.User;
import com.typeverse.exception.BusinessException;
import com.typeverse.repository.UserRepository;
import com.typeverse.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            throw new BusinessException("Password must be at least 6 characters");
        }

        if (request.getConfirmPassword() != null
                && !request.getPassword().equals(request.getConfirmPassword())) {
            throw new BusinessException("Passwords do not match");
        }

        String username = request.getUsername().toLowerCase().trim();
        String email = request.getEmail().toLowerCase().trim();

        if (userRepository.findByUsernameIgnoreCase(username).isPresent()) {
            throw new BusinessException("Username '" + username + "' is already taken");
        }

        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("Email '" + email + "' is already registered");
        }

        String resolvedFullName = (request.getFullName() != null && !request.getFullName().isBlank())
                ? request.getFullName().trim()
                : username;

        String passwordHash = passwordEncoder.encode(request.getPassword());
        User user = new User(username, email, passwordHash);
        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser.getId(), savedUser.getUsername(), "USER");

        return new AuthResponse(
                token,
                savedUser.getId(),
                savedUser.getUsername(),
                resolvedFullName,
                savedUser.getEmail(),
                "USER",
                86400000L // 24 hours
        );
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        String identifier = request.getUsernameOrEmail().toLowerCase().trim();

        User user = userRepository.findByUsernameIgnoreCase(identifier)
                .or(() -> userRepository.findByEmail(identifier))
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getId(), user.getUsername(), "USER");

        return new AuthResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getUsername(),
                user.getEmail(),
                "USER",
                86400000L
        );
    }
}