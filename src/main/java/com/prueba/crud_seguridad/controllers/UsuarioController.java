package com.prueba.crud_seguridad.controllers;

import com.prueba.crud_seguridad.entities.Usuario;
import com.prueba.crud_seguridad.service.implementation.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {

    public UsuarioController(UsuarioServiceImpl usuarioServiceimpl) {
        this.usuarioServiceimpl = usuarioServiceimpl;
    }

    private final UsuarioServiceImpl usuarioServiceimpl;

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> findAll(){
        return usuarioServiceimpl.findAll();
    }


    @PostMapping("/registrar")
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServiceimpl.save(usuario));
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Usuario> findByName( @PathVariable String nombre){
        Optional<Usuario> user = usuarioServiceimpl.findByUsername(nombre);
        if(user.isPresent()){
            return ResponseEntity.ok(user.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
