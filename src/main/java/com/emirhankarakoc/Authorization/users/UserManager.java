package com.emirhankarakoc.Authorization.users;


import com.emirhankarakoc.Authorization.exceptions.general.NotfoundException;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserManager implements UserService{

    @Override
    public String getAll() {
        return "dogru calisiyor.";
    }

}
