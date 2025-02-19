package com.prueba.crud_seguridad.service.implementation;

import com.prueba.crud_seguridad.entities.Role;
import com.prueba.crud_seguridad.request.AuthenticationRequest;
import com.prueba.crud_seguridad.request.AuthenticationResponse;
import com.prueba.crud_seguridad.security.jwt.JwtUtil;
import com.prueba.crud_seguridad.service.userDetails.UsuarioDetails;
import com.prueba.crud_seguridad.service.interfaces.ILoginService;
import com.prueba.crud_seguridad.service.interfaces.IUsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements ILoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest auth) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                auth.getUsername(), auth.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authToken);

        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();

        AuthenticationResponse response = jwtUtil.generateToken(usuarioDetails);

        response.setUsername(usuarioDetails.getUsername());

        response.setRole(jwtUtil.getListRoles(usuarioDetails));

        return response;

    }
}
