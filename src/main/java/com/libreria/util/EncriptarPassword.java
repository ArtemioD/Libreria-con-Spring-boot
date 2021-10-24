package com.libreria.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncriptarPassword {
    public static void main(String[] args) {
      String password = "123";
        System.out.println("password: " + password);
        System.out.println("password enctiptado:" + encriptarPassword(password));
    }
    
    public static String encriptarPassword(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }
    
}
