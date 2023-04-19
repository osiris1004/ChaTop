package com.backend.chatop.Auth.service;


import com.backend.chatop.Auth.RegisterRequest;
import com.backend.chatop.Auth.controller.AuthRequest;
import com.backend.chatop.Auth.controller.AuthResponse;
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
    private  final PasswordEncoder passwordEncoder;
    private  final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        //create a user object out of the RegisterRequest ^

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);

        //generate token
        var jwtToken = jwtService.generateToken(user);

        return  AuthResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        //to authenticate make use of au

        System.out.println(
                    "*******************************\n" +
                    request
                    +"\n********************************" 
                );
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        //get the user
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();


        //generate token
        var jwtToken = jwtService.generateToken(user);

        return  AuthResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
