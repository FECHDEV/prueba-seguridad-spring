package com.prueba.crud_seguridad.service;

import com.prueba.crud_seguridad.request.AutenticacionRequest;
import com.prueba.crud_seguridad.request.AuthenticationResponse;
import com.prueba.crud_seguridad.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService{

    private final AuthenticationManager authenticationManager;

    private final IUsuarioService iUsuarioService;

    private final JwtUtil jwtUtil;

    public LoginServiceImpl(AuthenticationManager authenticationManager, IUsuarioService iUsuarioService,  JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.iUsuarioService = iUsuarioService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthenticationResponse login(AutenticacionRequest auth) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                auth.getUsername(), auth.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authToken);

        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();


       // Usuario usuario = iUsuarioService.findByUsername(auth.getUsername()).orElseThrow();

        //return jwtUtil.generateToken(usuario);

        return jwtUtil.generateToken(usuarioDetails);

    }
}
