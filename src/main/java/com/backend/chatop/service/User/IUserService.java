package com.backend.chatop.service.User;

import java.util.List;

import com.backend.chatop.model.User.User;

public interface IUserService {

    List<User> getUsers();

    User getUserById(Integer id);
   
    User saveUser(User User);

}
