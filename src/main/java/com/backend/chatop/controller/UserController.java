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




@RestController
public class UserController {

    @Autowired
    private UserService userService;


   
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }



    @GetMapping("/api/auth/register/{id}")
    public ResponseEntity<User>getUserById(@PathVariable("id") Integer id){
       return ResponseEntity.ok(userService.getUserById(id));
   }
   


    @PostMapping("/api/auth/register")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }



    
}
