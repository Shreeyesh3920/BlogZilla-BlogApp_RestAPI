package com.BlogApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.BlogApp.Entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	public Optional<User> findUserByEmail(String email);
}
