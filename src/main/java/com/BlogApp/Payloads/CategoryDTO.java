package com.BlogApp.Payloads;

import java.util.List;

import com.BlogApp.Entities.Post;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class CategoryDTO {
	
	private int categoryId;
	@NotEmpty(message="Title cannot be empty")
	private String categoryTitle;
	@NotEmpty(message="Desciption cannot be empty")
	private String categoryDescription;
	
	
	
	
}
