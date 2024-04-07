package com.emirhankarakoc.Authorization.accounts;

import com.emirhankarakoc.Authorization.users.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    private final UserMiddleController middleware;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody LoginRequest r){
        return middleware.login(r);
    }
    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO register(@RequestBody RegisterRequest r){
        return middleware.register(r);
    }

    @PostMapping(value = "/test")
    public UserDTO testUserCreator(){
        return middleware.test();
    }

}
