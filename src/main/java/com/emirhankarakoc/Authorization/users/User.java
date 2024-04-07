package com.emirhankarakoc.Authorization.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String token;




    public static UserDTO userToDTO(User user){
        return UserDTO.builder()
                .id(user.id)
                .username(user.username)
                .token(user.token)
                .build();
    }
}
