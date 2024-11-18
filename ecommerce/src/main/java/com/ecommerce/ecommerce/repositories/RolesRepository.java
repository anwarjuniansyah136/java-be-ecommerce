package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Roles;

public interface RolesRepository extends JpaRepository<Roles,String>{
    Roles findByRolesName(String rolesName);
}
