package com.BlogApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.BlogApp.Service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import com.BlogApp.Payloads.*;
@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
	@Autowired
	private UserService userservice;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto)
	{
		UserDTO createUserDto=this.userservice.createUser(userdto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/showusers")
	public ResponseEntity<List<UserDTO>> showUsers()
	{
		List<UserDTO> userdtos=this.userservice.showAllUsers();
		return new ResponseEntity<>(userdtos,HttpStatus.FOUND);
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable @Positive(message="UserID must be Positive") Integer userid)
	{
		
			UserDTO userdto=this.userservice.getUserById(userid);
			return new ResponseEntity<>(userdto,HttpStatus.FOUND);
		
	}
	
	@PutMapping("/{userid}")
	public ResponseEntity<UserDTO> updateUser(@Valid @PathVariable @Positive Integer userid,@RequestBody @Valid UserDTO userdto){
		
		UserDTO updatedUser=this.userservice.updateUser(userdto, userid);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userid}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable @Positive Integer userid)
	{
		this.userservice.deleteUser(userid);
		return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteall")
	public ResponseEntity<ApiResponse> deleteAllUsers()
	{
		this.userservice.deleteAllUsers();
		return new ResponseEntity<>(new ApiResponse("All Users Deleted Successfully",true),HttpStatus.OK);
	}
}
