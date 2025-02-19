package com.prueba.crud_seguridad.service.userDetails;

import com.prueba.crud_seguridad.entities.Usuario;
import com.prueba.crud_seguridad.repository.IUsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final IUsuarioRepository iUsuarioRepository;

    public JpaUserDetailsService(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = iUsuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new UsuarioDetails(usuario);

        // Optional<Usuario> usuarioOptional = iUsuarioRepository.findByUsername(username);

        //investigar .format
     /*   if(usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema!", username));
        }

        Usuario usuario = usuarioOptional.orElseThrow();

        List<GrantedAuthority>authorities = usuario.getRoles().stream()
                .map( role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.isEnabled(),
                true,
                true,
                true,
                authorities);*/

    }


}
