package com.emirhankarakoc.Authorization.users;

import com.emirhankarakoc.Authorization.companies.Company;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDateTime createddatetime;
    private LocalDateTime updateddatetime;
    private String companyId;
    @OneToMany
    @JoinColumn(name = "companyId")
    private List<Company> workedCompanies;
    @Enumerated
    private List<UserType> userTypeList;
    private LocalDateTime birthDate;
    private String token;




    public static UserDTO userToDTO(User user){
        return UserDTO.builder()
                .id(user.id)
                .username(user.username)
                .firstname(user.firstname)
                .lastname(user.lastname)
                .createddatetime(user.createddatetime)
                .updateddatetime(user.updateddatetime)
                .companyId(user.companyId)
                .workedCompanies(user.workedCompanies)
                .userTypeList(user.userTypeList)
                .birthDate(user.birthDate)
                .build();
    }

    public static void updateUser(User user){
        user.setUpdateddatetime(LocalDateTime.now());
    }
}
