package com.prueba.crud_seguridad.security.jwt;

import com.prueba.crud_seguridad.entities.Role;
import com.prueba.crud_seguridad.request.AuthenticationResponse;
import com.prueba.crud_seguridad.service.userDetails.UsuarioDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public AuthenticationResponse generateToken(UsuarioDetails user) {

        Map<String, Object> claims = getMapAuthorities(user);

        String jwt = Jwts.builder()
                .subject(user.getUsername())
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 360000))
                /*.expiration(new Date(System.currentTimeMillis() + 3))*/
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

    public Map<String, Object> getMapAuthorities(UsuarioDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getAuthorities().stream()
                //.map(authority -> authority.getAuthority())
                .map(GrantedAuthority::getAuthority).toList());
        return claims;
    }

    public UsernamePasswordAuthenticationToken extractClaims(String token) {
        Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
        System.out.println(claims + "muestra los claims cuando se utiliza un token");
        String username = claims.getSubject();

        List<SimpleGrantedAuthority> roles = getListAuthorities(claims);

        return new UsernamePasswordAuthenticationToken(username, null, roles);
    }

    private List<SimpleGrantedAuthority> getListAuthorities(Claims claims) {
        Object authorities = claims.get("roles");

        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        if (authorities instanceof List<?>) {
            /*roles = ((List<?>) authorities).stream()
                    .filter(obj -> obj instanceof String)
                    .map(obj -> (String) obj)
                    .map(role -> new SimpleGrantedAuthority(role))
                    .toList();*/
            roles = ((List<?>) authorities).stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .map(SimpleGrantedAuthority::new)
                    .toList();
        } else {
            return roles;
        }

        return roles;
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);

        } catch (ExpiredJwtException e) {
            // Manejo del caso en que el token ha expirado
            System.out.println("El token ha expirado");
        } catch (SignatureException e) {
            // Manejo de error de firma inválida
            System.out.println("La firma del token es inválida");
        } catch (MalformedJwtException e) {
            // Manejo de error de token mal formado
            System.out.println("El token está mal formado");
        } catch (JwtException e) {

        }

    }

    public List<Role> getListRoles(UsuarioDetails usuarioDetails) {

        return usuarioDetails.getAuthorities().stream()
                .map(grantedAuthority -> new Role(grantedAuthority.getAuthority()))
                .toList();
    }
}
