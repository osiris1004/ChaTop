package com.backend.chatop.service.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.backend.chatop.errors.GlobalExceptionHandler.ResourceNotFoundException;
import com.backend.chatop.model.User.User;
import com.backend.chatop.repository.UserRepository;
import lombok.*;


@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }  throw  new RuntimeException(email+ "does not exist");
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> rental = userRepository.findById(id);
        if(rental.isPresent()){
            return rental.get();
        }
        throw  new ResourceNotFoundException("Not found rentals with id = " + id);
    }

}
