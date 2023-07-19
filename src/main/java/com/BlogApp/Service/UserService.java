package com.BlogApp.Service;

import java.util.List;
import com.BlogApp.Payloads.UserDTO;

public interface UserService {

	public UserDTO createUser(UserDTO user);
	public List<UserDTO> showAllUsers();
	public UserDTO getUserById(Integer userId);
	public UserDTO updateUser(UserDTO user, Integer userId);
	public void deleteUser(Integer userId);
	public void deleteAllUsers();
	
}
