package com.backend.chatop.model.User;
import org.springframework.http.ResponseEntity;

import jakarta.persistence.*;
import lombok.*;



@Data   


@Entity(name = "user") 
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private  Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public ResponseEntity<User> orElseThrow(Object object) {
        return null;
    } 
    
}
