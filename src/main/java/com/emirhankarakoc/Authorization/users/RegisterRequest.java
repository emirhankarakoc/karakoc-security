package com.emirhankarakoc.Authorization.users;


import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
