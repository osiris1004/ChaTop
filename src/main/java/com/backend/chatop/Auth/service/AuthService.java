package com.backend.chatop.Auth.service;

import com.backend.chatop.Auth.authenticate.AuthRequest;
import com.backend.chatop.Auth.authenticate.AuthResponse;
import com.backend.chatop.Auth.registry.RegisterRequest;
import com.backend.chatop.config.middleware.JwtService;
import com.backend.chatop.model.User.Role;
import com.backend.chatop.model.User.User;
import com.backend.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthResponse register(RegisterRequest request) {
                var user = User
                                .builder()
                                .name(request.getFirstName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.USER)
                                .build();

                repository.save(user);

                var jwtToken = jwtService.generateToken(user);

                return AuthResponse
                                .builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthResponse authenticate(AuthRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

                var user = repository.findByEmail(request.getEmail()).orElseThrow();

                var jwtToken = jwtService.generateToken(user);

                return AuthResponse
                                .builder()
                                .token(jwtToken)
                                .build();
        }
}
