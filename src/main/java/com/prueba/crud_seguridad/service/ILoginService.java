package com.prueba.crud_seguridad.service;

import com.prueba.crud_seguridad.entities.Usuario;
import com.prueba.crud_seguridad.request.AutenticacionRequest;
import com.prueba.crud_seguridad.request.AuthenticationResponse;

public interface ILoginService {

    AuthenticationResponse login(AutenticacionRequest auth);
}
