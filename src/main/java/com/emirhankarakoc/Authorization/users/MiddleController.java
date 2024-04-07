package com.emirhankarakoc.Authorization.users;


import com.emirhankarakoc.Authorization.exceptions.general.BadRequestException;
import com.emirhankarakoc.Authorization.exceptions.general.NotfoundException;
import com.emirhankarakoc.Authorization.jwt.AccountService;
import com.emirhankarakoc.Authorization.jwt.JWTService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.emirhankarakoc.Authorization.users.User.userToDTO;

@Service
@Slf4j
@AllArgsConstructor
public class MiddleController {
    private final UserService userService;
    private final AccountService accountService;
    private final UserRepository repository;
    private final JWTService jwtService;


    public String getAll(String token){
        jwtService.validateToken(token);
        //buraya repository.findbytoken.isAdmin kontrolu koymaliyiz.
        log.info("Birilerinin tokeni dogru calisiyor ve islem tamamlaniyor");
        return userService.getAll();

    }

    public UserDTO login(LoginRequest request){
        return accountService.login(request);

    }

    public UserDTO register(RegisterRequest request){
        return accountService.register(request);
    }
}
