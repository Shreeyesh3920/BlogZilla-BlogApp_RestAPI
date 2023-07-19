package com.BlogApp.Payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

	

	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private int totalElements;
	private long totalPages;
	
	private boolean lastPage;

	
	
}
