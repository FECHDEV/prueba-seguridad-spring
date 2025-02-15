package com.prueba.crud_seguridad.security.jwt;

import com.prueba.crud_seguridad.request.AuthenticationResponse;
import com.prueba.crud_seguridad.service.UsuarioDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtUtil {

    //public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    @Value("${jwt.secret}")
    private String secret;


    public AuthenticationResponse generateToken(UsuarioDetails user){

        Map<String, Object> claims = getMapAuthorities(user);

        String jwt =  Jwts.builder()
                .subject(user.getUsername())
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 360000))
                .issuedAt(new Date())
                .signWith(getSecretKey())
                .compact();

        AuthenticationResponse auth = new AuthenticationResponse();
        auth.setJwt(jwt);

        return auth;
    }


    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private static Map<String, Object> getMapAuthorities(UsuarioDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getAuthorities().stream()
                //.map(authority -> authority.getAuthority())
                .map(GrantedAuthority::getAuthority)
                .toList());
        return claims;
    }


}
