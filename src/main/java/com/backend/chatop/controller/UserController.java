package com.backend.chatop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop.model.User.User;
import com.backend.chatop.service.User.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/api/user/")
    public ResponseEntity<String> getUserById() {
        return ResponseEntity.ok("hellow man ");
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getRentalById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
