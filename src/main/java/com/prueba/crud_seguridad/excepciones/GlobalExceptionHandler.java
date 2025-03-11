package com.prueba.crud_seguridad.excepciones;

import com.prueba.crud_seguridad.Dto.ApiError;
import com.prueba.crud_seguridad.excepciones.custom.InvalidTokenException;
import com.prueba.crud_seguridad.excepciones.custom.InvalidDataException;
import com.prueba.crud_seguridad.excepciones.custom.usernameNotfoundException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //no funcinoa con las excepciones del filtro del token
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiError> invalid(InvalidTokenException ex, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setExceptionType(ex.getClass().getSimpleName());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("Token invalido, verifique las credenciales de su token");
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest request, Exception exception){

        ApiError apiError = new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setExceptionType(exception.getClass().getSimpleName());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("Error interno en el servidor, vuelva a intentarlo");
        apiError.setTimestamp(LocalDateTime.now() );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> credenialesErroneasExcepcion(BadCredentialsException ex, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setExceptionType(ex.getClass().getSimpleName());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("Credenciales incorrectas, verifique su usuario y contraseña");
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    /*@ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> ErrorToken(JwtException ex, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setExceptionType(ex.getClass().getSimpleName());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("Token invalido, verifique las credenciales de su token");
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }*/

    @ExceptionHandler(usernameNotfoundException.class)
    public ResponseEntity<ApiError> usuarioNoEncontrado(usernameNotfoundException ex, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setExceptionType(ex.getClass().getSimpleName());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("El Usuario que busca no existe en la BD");
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiError> ErrorSintaxix(InvalidDataException ex, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setExceptionType(ex.getClass().getSimpleName());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("jadklsjkldslkdjaslkdjsajdsklajdasjdlkasjdklasdjlksajdlksajdlasjd");
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> ArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setExceptionType(ex.getClass().getSimpleName());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("Usuario y/o contraseña no son invalidos");
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


}
