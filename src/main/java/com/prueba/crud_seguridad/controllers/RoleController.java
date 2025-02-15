package com.prueba.crud_seguridad.controllers;

import com.prueba.crud_seguridad.entities.Role;
import com.prueba.crud_seguridad.service.IRoleService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {


    private IRoleService iRoleService;

    public RoleController(IRoleService iRoleService) {
        this.iRoleService = iRoleService;
    }

    @GetMapping
    public List<Role> findAll(){
        return iRoleService.findAll();
    }
}
