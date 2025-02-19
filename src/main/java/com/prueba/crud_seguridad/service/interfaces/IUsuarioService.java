package com.prueba.crud_seguridad.service.interfaces;

import com.prueba.crud_seguridad.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> findByUsername(String username);

    Usuario save(Usuario usuario);



}
