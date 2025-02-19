package com.prueba.crud_seguridad.request;

import com.prueba.crud_seguridad.entities.Role;

import java.util.List;

public class AuthenticationResponse {

    private String jwt;

    private String username;

    private List<Role> role;

    public AuthenticationResponse(String jwt, String username, List<Role> role) {
        this.jwt = jwt;
        this.username = username;
        this.role = role;
    }

    public AuthenticationResponse(){

    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
