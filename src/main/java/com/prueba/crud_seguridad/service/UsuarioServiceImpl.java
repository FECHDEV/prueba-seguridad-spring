package com.prueba.crud_seguridad.service;

import com.prueba.crud_seguridad.entities.Role;
import com.prueba.crud_seguridad.entities.Usuario;
import com.prueba.crud_seguridad.repository.IRoleRRepository;
import com.prueba.crud_seguridad.repository.IUsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

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


        Optional<Role> optionalRoleUsuario = iRoleRRepository.findByName("ROLE_USUARIO");
        List<Role>roles =new ArrayList<>();

        optionalRoleUsuario.ifPresent(roles::add);

        if(usuario.isAdmin()){
            Optional<Role>optionalRoleAdmin = iRoleRRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
            /*optionalRoleAdmin.ifPresent(role -> roles.add(role));*/
        }

        usuario.setRoles(roles);
        usuario.setEnabled(true);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return iUsuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return iUsuarioRepository.findByUsername(username);
    }
}
