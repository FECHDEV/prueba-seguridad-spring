package com.prueba.crud_seguridad.service.interfaces;

import com.prueba.crud_seguridad.request.AuthenticationRequest;
import com.prueba.crud_seguridad.request.AuthenticationResponse;

public interface ILoginService {

    AuthenticationResponse login(AuthenticationRequest auth);
}
