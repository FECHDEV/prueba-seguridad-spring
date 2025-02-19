package com.prueba.crud_seguridad.service.interfaces;

import com.prueba.crud_seguridad.entities.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();
}
