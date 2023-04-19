package com.backend.chatop.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.backend.chatop.model.User.User;
import com.backend.chatop.service.User.UserService;

import lombok.RequiredArgsConstructor;




@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    //private final UserService userService;


   
//     @GetMapping("/api/auth/users")
//     public ResponseEntity<List<User>> getUsers(){
//         return ResponseEntity.ok(userService.getUsers());
//     }



//     @GetMapping("/api/auth/user/{id}")
//     public ResponseEntity<User>getUserById(@PathVariable("id") Integer id){
//         System.out.println("test");
//        return ResponseEntity.ok(userService.getUserById(id));
//    }

@GetMapping("/api/user/")
public ResponseEntity<String>getUserById(){
   return ResponseEntity.ok("hellow man ");
}
   


//     @PostMapping("/api/auth/register")
//     public ResponseEntity<User> saveUser(@RequestBody User user){
//         return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
//     }



    
}
