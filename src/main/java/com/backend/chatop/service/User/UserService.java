package com.backend.chatop.service.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.backend.chatop.model.User.User;
import com.backend.chatop.repository.UserRepository;
import lombok.*;


@RequiredArgsConstructor
@Service 
public class UserService implements IUserService{

    
    private final UserRepository userRepository;

    @Override
    public List<User> getUsers(){
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw  new RuntimeException("user is not found for the id "+id); // this nay return an optional object
    }
   
    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }
    
}
