package com.backend.chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.chatop.model.User.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
    
}
