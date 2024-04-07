package com.emirhankarakoc.Authorization.accounts;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.emirhankarakoc.Authorization.exceptions.general.BadRequestException;
import com.emirhankarakoc.Authorization.exceptions.general.NotfoundException;
import com.emirhankarakoc.Authorization.jwt.JWTService;
import com.emirhankarakoc.Authorization.users.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static com.emirhankarakoc.Authorization.users.User.updateUser;
import static com.emirhankarakoc.Authorization.users.User.userToDTO;

@Service
@AllArgsConstructor
public class AccountService {
    private final UserRepository repository;
    private final JWTService jwtService;


    public String login(LoginRequest request){
        User user = repository.findByUsername(request.getUsername()).orElseThrow(()-> new NotfoundException("User not found."));

        if (BCrypt.verifyer().verify(request.getPassword().toCharArray(), user.getPassword()).verified) {
            user.setToken(jwtService.generateToken(user.getUsername()));
            repository.save(user);
            return user.getToken();

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
        user.setCreateddatetime(LocalDateTime.now());
        user.setUsername(request.getUsername());
        // Şifreyi BCrypt ile hashle
        String hashedPassword = BCrypt.withDefaults().hashToString(12, request.getPassword().toCharArray());
        user.setPassword(hashedPassword);
        //hashle
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setBirthDate(request.getBirthDate());
        user.setUserTypeList(new ArrayList<>());
        user.getUserTypeList().add(request.getUserType());

        user.setToken(jwtService.generateToken(user.getUsername()));
        updateUser(user);
        return userToDTO(repository.save(user));
    }

}
