package com.BlogApp.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Entities.User;
import com.BlogApp.Payloads.UserDTO;
import com.BlogApp.Repository.UserRepo;
import com.BlogApp.Exceptions.*;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userrepo;
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public UserDTO createUser(UserDTO userdto) {
		User user=this.dtoToUser(userdto);
		User savedUser=this.userrepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public List<UserDTO> showAllUsers() {
		
		List<User> userlist=this.userrepo.findAll();
		List<UserDTO> userdtolist=new ArrayList<UserDTO>();
		if(!userlist.isEmpty()) 
		{
			for(User ud:userlist) 
			{
				userdtolist.add(this.userToDto(ud));
			}
			return userdtolist;
		}
		else 
		{
			throw new UserNotFoundException("No Data of Users Found");
		}
		
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		
		User user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		UserDTO userdto=this.userToDto(user);
		return userdto;
	}

	
	
	@Override
	public UserDTO updateUser(UserDTO userdto, Integer userId){
		
		User user=this.userrepo.findById(userId) 
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		
		this.userrepo.save(user);
		return this.modelmapper.map(user, UserDTO.class);
		}

	
	
	@Override
	public void deleteUser(Integer userId) {
		
		User user=this.userrepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userrepo.delete(user);
	}
	
	@Override
	public void deleteAllUsers() {
		
		List<User>users=this.userrepo.findAll();
		if(!users.isEmpty()) 
		{
			this.userrepo.deleteAll();
		}
		else {
		throw new UserNotFoundException("No Users Are Found to delete");
		}
	} 
	
	//dto-To-User
	public User dtoToUser(UserDTO userdto) 
	{
		User user=this.modelmapper.map(userdto, User.class);
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		
		return user;
	}
	//user-to-dto
	public UserDTO userToDto(User user) 
	{
		UserDTO userdto=this.modelmapper.map(user, UserDTO.class);
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());
		
		
		return userdto;
	}

	
}
