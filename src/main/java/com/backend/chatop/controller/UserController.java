package com.backend.chatop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;




@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    //private final UserService userService;


   


@GetMapping("/api/user/")
public ResponseEntity<String>getUserById(){
   return ResponseEntity.ok("hellow man ");
}
   
}
