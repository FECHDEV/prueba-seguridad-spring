package com.prueba.crud_seguridad.service;

import com.prueba.crud_seguridad.entities.Role;
import com.prueba.crud_seguridad.repository.IRoleRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRoleServiceImpl implements IRoleService{

    @Autowired
    private IRoleRRepository iRoleRRepository;

    public IRoleServiceImpl(IRoleRRepository iRoleRRepository) {
        this.iRoleRRepository = iRoleRRepository;
    }

    @Override
    public List<Role> findAll() {
        return iRoleRRepository.findAll();
    }
}
