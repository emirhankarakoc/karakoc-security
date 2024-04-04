package com.emirhankarakoc.Authorization.jwt;

import com.emirhankarakoc.Authorization.exceptions.general.BadRequestException;
import com.emirhankarakoc.Authorization.exceptions.general.NotfoundException;
import com.emirhankarakoc.Authorization.users.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.emirhankarakoc.Authorization.users.User.userToDTO;

@Service
@AllArgsConstructor
public class AccountService {
    private final UserRepository repository;
    private final JWTService jwtService;

    public UserDTO login(LoginRequest request){
        User user = repository.findByUsername(request.getUsername()).orElseThrow(()-> new NotfoundException("User not found."));
        if (user.getPassword().equals(request.getPassword())) {
            user.setToken(jwtService.generateToken(request.getUsername()));
            return userToDTO(repository.save(user));
        }
        else{
            throw new BadRequestException("Username and password do not match."); // sifreler eslesmiyorsa.
        }

    }

    public UserDTO register(RegisterRequest request){
        Optional<User> testUser = repository.findByUsername(request.getUsername());
        if (testUser.isPresent()){
            throw new BadRequestException("This username already taken.");
        }
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setToken(jwtService.generateToken(user.getUsername()));
        return userToDTO(repository.save(user));
    }

}
