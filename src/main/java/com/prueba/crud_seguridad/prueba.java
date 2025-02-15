package com.prueba.crud_seguridad;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class prueba {


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String pass(){
        return passwordEncoder.encode("123456");
    }

    public static void main(String[] args) {
        System.out.println(pass());

    }
}
