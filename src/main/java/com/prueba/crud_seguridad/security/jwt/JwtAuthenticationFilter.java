package com.prueba.crud_seguridad.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.crud_seguridad.Dto.ApiError;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response); // Continúa sin procesar el token
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);

        try {
            jwtUtil.validateToken(jwt);

            UsernamePasswordAuthenticationToken authentication = jwtUtil.extractClaims(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Usuario autenticado: " + authentication.getName());
            System.out.println("Roles: " + authentication.getAuthorities());
        }catch (JwtException e){

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            ApiError apiError = new ApiError();
            apiError.setBackendMessage(e.getLocalizedMessage());
            apiError.setExceptionType(e.getClass().getSimpleName());
            apiError.setUrl(request.getRequestURL().toString());
            apiError.setMethod(request.getMethod());
            apiError.setMessage("Token inválido, verifique las credenciales de su token");
            String jsonResponse = new ObjectMapper().writeValueAsString(apiError);
            response.getWriter().write(jsonResponse);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
