package com.emirhankarakoc.Authorization.jwt;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CustomConfigService {

   private final String secretKey = "111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc";
    private final long expirationDate = System.currentTimeMillis() + 1000 * 60*15; //15 dk
    //dont change long to Date... danger...warning.!!!!!!

}
