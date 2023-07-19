package com.BlogApp.Payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.BlogApp.Entities.Category;
import com.BlogApp.Entities.Comment;
import com.BlogApp.Entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	private int postid;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	private CategoryDTO category;
	private UserDTO user;
	private List<CommentDTO> comments=new LinkedList<>();
}
