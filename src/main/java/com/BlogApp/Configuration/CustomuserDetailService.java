package com.BlogApp.Configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BlogApp.Entities.User;
import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.Repository.UserRepo;


@Service
public class CustomuserDetailService implements UserDetailsService{
@Autowired
UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=this.userRepo.findUserByEmail(email).orElseThrow(()-> new ResourceNotFoundException("user","email:"+email,0));
		return user;
	}
}