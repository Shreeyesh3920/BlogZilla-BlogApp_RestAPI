package com.BlogApp.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Configuration.APPConstant;
import com.BlogApp.Payloads.ApiResponse;
import com.BlogApp.Payloads.PostDTO;
import com.BlogApp.Payloads.PostResponse;
import com.BlogApp.Service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postservice;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody @Valid PostDTO postdto,
			@PathVariable @Valid Integer userId,
			@PathVariable @Valid Integer categoryId)
	{
		PostDTO pdto=this.postservice.createPost(postdto,userId,categoryId);
		return new ResponseEntity<PostDTO>(pdto,HttpStatus.CREATED);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/user/{userId}/Posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDTO>pdtos=this.postservice.getPostsByUser(userId);
		return new ResponseEntity<List<PostDTO>>(pdtos,HttpStatus.FOUND);
	}
	
	@GetMapping("/category/{categoryId}/Posts")
	public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
			@RequestParam(value="pageNumber",defaultValue =APPConstant.PageNumber, required = false)Integer PageNumber,
			@RequestParam(value="pageSize",defaultValue =APPConstant.PageSize, required = false)Integer PageSize
			)
	{
		PostResponse postresponse=this.postservice.getPostByCategory(categoryId, PageNumber, PageSize);
		return new ResponseEntity<>(postresponse,HttpStatus.FOUND);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> showAllPosts(
	    @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
	    @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
	    @RequestParam(value = "sortBy", defaultValue = "postid", required = false)String sortBy,
	    @RequestParam(value = "sortDirc", defaultValue = "asc", required = false)String sortDirc
			
		) {
	    PostResponse postresponse=this.postservice.getAllPost(pageNumber, pageSize,sortBy,sortDirc);

	    return new ResponseEntity<PostResponse>(postresponse, HttpStatus.OK);
	}

	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable @Valid Integer postId)
	{
		PostDTO pdto=this.postservice.getPostById(postId);
		return new ResponseEntity<PostDTO>(pdto,HttpStatus.CREATED);
		
	}
	
	
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody @Valid PostDTO postdto,@PathVariable @Valid Integer postId)
	{
		PostDTO pdto=this.postservice.updatePost(postdto, postId);
		return new ResponseEntity<PostDTO>(pdto,HttpStatus.CREATED);
		
	}
	
	
	
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId)
	{
		this.postservice.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
		
	}
	
	//search
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDTO>> searchByTitle(@PathVariable String keyword)
	{
		List<PostDTO> pdto=this.postservice.searchPosts(keyword);
		return new ResponseEntity<>(pdto,HttpStatus.FOUND);
	}
}
