package com.BlogApp.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.Payloads.CommentDTO;
import com.BlogApp.Repository.CommentRepo;
import com.BlogApp.Repository.PostRepo;
import com.BlogApp.Entities.*;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postrepo;
	@Autowired
	private CommentRepo commentrepo;
	@Autowired
	private ModelMapper modelmapper;
	@Override
	public CommentDTO createComment(Integer postId, CommentDTO cDTO) {
		Post post=this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
		Comment comment=this.modelmapper.map(cDTO,Comment.class);
		comment.setPost(post);
		
		Comment savedComment=this.commentrepo.save(comment);
		return this.modelmapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment=this.commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","comment Id",commentId));
		this.commentrepo.delete(comment);
	}

}
