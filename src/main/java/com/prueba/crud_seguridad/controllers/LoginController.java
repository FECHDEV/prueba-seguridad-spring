package com.prueba.crud_seguridad.controllers;

import com.prueba.crud_seguridad.request.AuthenticationRequest;
import com.prueba.crud_seguridad.request.AuthenticationResponse;
import com.prueba.crud_seguridad.service.interfaces.ILoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final ILoginService iLoginService;

    public LoginController(ILoginService iLoginService) {
        this.iLoginService = iLoginService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iLoginService.login(request));
    }
}
