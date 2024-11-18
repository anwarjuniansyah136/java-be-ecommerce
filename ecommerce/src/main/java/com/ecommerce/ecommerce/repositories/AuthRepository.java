package com.ecommerce.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Auth;
import com.ecommerce.ecommerce.model.Roles;

public interface AuthRepository extends JpaRepository<Auth,String>{
    Auth findByEmail(String email);

    List<Auth> findByRoles(Roles admin);
}
