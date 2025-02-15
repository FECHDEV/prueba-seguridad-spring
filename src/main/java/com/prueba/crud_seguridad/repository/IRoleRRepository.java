package com.prueba.crud_seguridad.repository;

import com.prueba.crud_seguridad.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRRepository extends JpaRepository<Role,Long> {
        Optional<Role> findByName(String name);

}
