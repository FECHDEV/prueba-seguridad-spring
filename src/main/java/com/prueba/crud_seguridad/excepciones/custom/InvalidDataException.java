package com.prueba.crud_seguridad.excepciones.custom;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message) {
        super(message);
    }
}
