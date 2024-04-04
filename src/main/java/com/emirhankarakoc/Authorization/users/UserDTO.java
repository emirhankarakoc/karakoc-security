package com.emirhankarakoc.Authorization.users;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Builder
public class UserDTO {
    private String id;
    private String username;
    private String token;
}
