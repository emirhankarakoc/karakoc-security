package com.emirhankarakoc.Authorization.users;


import com.emirhankarakoc.Authorization.accounts.AccountService;
import com.emirhankarakoc.Authorization.exceptions.general.BadRequestException;
import com.emirhankarakoc.Authorization.exceptions.general.ForbiddenException;
import com.emirhankarakoc.Authorization.exceptions.general.NotfoundException;
import com.emirhankarakoc.Authorization.exceptions.general.UnauthorizatedException;
import com.emirhankarakoc.Authorization.jwt.JWTService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.emirhankarakoc.Authorization.users.User.userToDTO;

@Service
@Slf4j
@AllArgsConstructor
public class UserMiddleController {
    private final UserService userService;
    private final AccountService accountService;
    private final UserRepository repository;
    private final JWTService jwtService;


    public String getAll(String token){
        jwtService.validateToken(token);
        User user = repository.findByToken(token).orElseThrow(()-> new NotfoundException("User not found."));

        if (user.getUserTypeList().contains(UserType.SERVER_ADMIN)){
            log.info("Adminin tokeni dogru calisiyor ve islem tamamlaniyor");
            return userService.getAll();
        }
        else {
            throw new ForbiddenException("Forbidden");
        }


    }

    public String login(LoginRequest request){
        return accountService.login(request);

    }

    public UserDTO register(RegisterRequest request){
        if (request.getUserType().equals(UserType.SERVER_ADMIN)){
            throw new BadRequestException("sen beni gerizekali mi zannettin :)");
        }
        return accountService.register(request);
    }
    public UserDTO test(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("emirhan");
        user.setToken(jwtService.generateToken("emirhan"));
        repository.save(user);
        return userToDTO(user);

    }
}
