package com.prueba.crud_seguridad.security.jwt;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

//esta clase no funcionan en mi configuracion
public class tokenJwtConfig {
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
}
