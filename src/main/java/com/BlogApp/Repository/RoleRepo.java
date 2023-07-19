package com.BlogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.Entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer>{

}
