package com.backend.chatop.service.User;
import com.backend.chatop.model.User.User;

public interface IUserService {
    User getUserByEmail(String email);
    User getUserById(Integer id);
}
