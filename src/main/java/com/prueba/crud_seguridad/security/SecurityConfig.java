package com.prueba.crud_seguridad.security;


import com.prueba.crud_seguridad.security.jwt.JwtUtil;
import com.prueba.crud_seguridad.service.IUsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final IUsuarioService iUsuarioService;

    public SecurityConfig(AuthenticationManager authenticationManager, JwtUtil jwtUtil, IUsuarioService iUsuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.iUsuarioService = iUsuarioService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                //.csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.authenticationProvider(authenticationProvider)
                .authorizeHttpRequests( config -> config
                        .requestMatchers(HttpMethod.GET, "/api/usuario").authenticated()
                        .anyRequest().permitAll())
                //.addFilter(new LoginServiceImpl(authenticationManager, jwtUtil,iUsuarioService),UsernamePasswordAuthenticationFilter.class)
               .build();
    }


}
