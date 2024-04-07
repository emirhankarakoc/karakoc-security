package com.emirhankarakoc.Authorization.users;

import com.emirhankarakoc.Authorization.companies.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class UserDTO {
    private String id;
    private String username;
    private String firstname;
    private String lastname;
    private LocalDateTime createddatetime;
    private LocalDateTime updateddatetime;
    private String companyId;
    private List<Company> workedCompanies;
    private List<UserType> userTypeList;

    private LocalDateTime birthDate;
}
