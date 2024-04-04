package com.emirhankarakoc.Authorization.users;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {
    private String id;
    private String username;
    private String token;
}
