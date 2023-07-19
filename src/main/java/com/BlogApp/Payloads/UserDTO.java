package com.BlogApp.Payloads;
import java.util.List;

import com.BlogApp.Entities.Post;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class UserDTO {
	private int id;
	
	@NotEmpty(message="Name cannot be Null")
	@Size(min=4,message="Username must be min size of 4")
	private String name;
	
	@NotEmpty(message="Email cannot be empty")
	@Email(message="Email ID is not Valid")
	private String email;
	
	@NotEmpty(message="Password cannot be Null")
	@Size(min=3, max=10,message="Password must be min of 3 Charatcters and maximum of 10 Characters")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$"
	,message="Minimum eight and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character Required")
	private String password;
	
	@NotEmpty(message="Please write about yourself")
	private String about;
	
	
	
}
