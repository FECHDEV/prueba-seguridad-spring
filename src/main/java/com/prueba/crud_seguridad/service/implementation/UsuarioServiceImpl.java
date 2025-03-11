package com.prueba.crud_seguridad.service.implementation;

import com.prueba.crud_seguridad.entities.Role;
import com.prueba.crud_seguridad.entities.Usuario;
import com.prueba.crud_seguridad.excepciones.custom.InvalidDataException;
import com.prueba.crud_seguridad.excepciones.custom.usernameNotfoundException;
import com.prueba.crud_seguridad.repository.IRoleRRepository;
import com.prueba.crud_seguridad.repository.IUsuarioRepository;
import com.prueba.crud_seguridad.service.interfaces.IUsuarioService;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    //@Autowired
    private final IUsuarioRepository iUsuarioRepository;

    private final IRoleRRepository iRoleRRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(IUsuarioRepository iUsuarioRepository, IRoleRRepository iRoleRRepository, PasswordEncoder passwordEncoder) {
        this.iUsuarioRepository = iUsuarioRepository;
        this.iRoleRRepository = iRoleRRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return iUsuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {

        Optional<Usuario> existUser = iUsuarioRepository.findByUsername(usuario.getUsername());
        if(existUser.isPresent()){
            throw new InvalidDataException("El nombre del usuario ya esta en uso");
        }






        Optional<Role> optionalRoleUsuario = iRoleRRepository.findByName("ROLE_USUARIO");
        List<Role>roles =new ArrayList<>();

        optionalRoleUsuario.ifPresent(roles::add);

        if(usuario.getAdmin() != null){
            Optional<Role>optionalRoleAdmin = iRoleRRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
            /*optionalRoleAdmin.ifPresent(role -> roles.add(role));*/
        }else{
            System.out.println("sin admin");
        }

        usuario.setRoles(roles);
        usuario.setEnabled(true);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return iUsuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {

        Optional<Usuario> usuario = iUsuarioRepository.findByUsername(username);
        if(usuario.isEmpty()){
            throw new usernameNotfoundException("el usuario no existe en la BD");
        }
        return usuario;
    }
}
