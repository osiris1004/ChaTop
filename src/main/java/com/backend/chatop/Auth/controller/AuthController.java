package com.backend.chatop.Auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.chatop.Auth.authenticate.AuthRequest;
import com.backend.chatop.Auth.authenticate.AuthResponse;
import com.backend.chatop.Auth.registry.RegisterRequest;
import com.backend.chatop.Auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register( @RequestBody RegisterRequest request ){
        return  ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> register( @RequestBody AuthRequest request ){
        return  ResponseEntity.ok(service.authenticate(request));
    }
}
