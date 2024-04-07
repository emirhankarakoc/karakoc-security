package com.emirhankarakoc.Authorization.users;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
private final UserMiddleController middleware;


    @GetMapping
    public String getAll(String token){
        return middleware.getAll(token);
    }





}
