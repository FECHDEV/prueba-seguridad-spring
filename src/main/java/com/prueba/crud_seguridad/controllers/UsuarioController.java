package com.prueba.crud_seguridad.controllers;

import com.prueba.crud_seguridad.entities.Usuario;
import com.prueba.crud_seguridad.request.AuthenticationRequest;
import com.prueba.crud_seguridad.request.AuthenticationResponse;
import com.prueba.crud_seguridad.service.interfaces.ILoginService;
import com.prueba.crud_seguridad.service.implementation.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    public UsuarioController(UsuarioServiceImpl usuarioServiceimpl, ILoginService iLoginService) {
        this.usuarioServiceimpl = usuarioServiceimpl;
        this.iLoginService = iLoginService;
    }

    private final UsuarioServiceImpl usuarioServiceimpl;

    //private final IUsuarioService iUsuarioService;

    private final ILoginService iLoginService;

   // private final AuthenticationManager authenticationManager;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> findAll(){
        return usuarioServiceimpl.findAll();
    }


    @PostMapping("/registrar")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
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

   /* @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        //AuthenticationResponse auth = iLoginService.login(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iLoginService.login(request));
    }*/


   /* @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AutenticacionRequest auth) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword())
            );

            //SecurityContextHolder.getContext().setAuthentication(authentication);

            Authentication authCheck = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Usuario autenticado: " + (authCheck != null ? authCheck.getName() : "No autenticado"));
            System.out.println("Roles: " + (authCheck != null ? authCheck.getAuthorities() : "No tiene roles"));


            return ResponseEntity.ok("Usuario " + auth.getUsername() + " autenticado correctamente");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }*/

    /*@PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falta el encabezado Authorization");
        }

        // Extraer y decodificar las credenciales (Basic Base64(username:password))
        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2); // username y password

        String username = values[0];
        String password = values[1];

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok("Usuario " + username + " autenticado correctamente");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }*/
}
