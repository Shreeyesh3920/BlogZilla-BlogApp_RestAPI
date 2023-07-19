package com.BlogApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Payloads.ApiResponse;
import com.BlogApp.Payloads.CommentDTO;
import com.BlogApp.Service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	CommentService commentservice;
	
	@PostMapping("/post/{postId}/comment/")
	ResponseEntity<CommentDTO> createComment(@PathVariable Integer postId,@RequestBody CommentDTO commentdto)		
	{
		CommentDTO cdto=this.commentservice.createComment(postId, commentdto);
		
		return new ResponseEntity<>(cdto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/post/comment/{commentId}")
	ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)		
	{
		this.commentservice.deleteComment(commentId);
		
		return new ResponseEntity<>(new ApiResponse("Comment Deleted Sucessfully",true),HttpStatus.FOUND);
	}
}
