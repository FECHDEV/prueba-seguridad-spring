package com.prueba.crud_seguridad.service.implementation;

import com.prueba.crud_seguridad.entities.Role;
import com.prueba.crud_seguridad.repository.IRoleRRepository;
import com.prueba.crud_seguridad.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRRepository iRoleRRepository;

    public RoleServiceImpl(IRoleRRepository iRoleRRepository) {
        this.iRoleRRepository = iRoleRRepository;
    }

    @Override
    public List<Role> findAll() {
        return iRoleRRepository.findAll();
    }
}
