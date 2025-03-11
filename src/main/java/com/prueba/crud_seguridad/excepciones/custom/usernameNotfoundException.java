package com.prueba.crud_seguridad.excepciones.custom;

public class usernameNotfoundException extends RuntimeException{

    public usernameNotfoundException(String message) {
        super(message);
    }
}
