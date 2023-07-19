package com.BlogApp.Service;

import com.BlogApp.Payloads.CommentDTO;

public interface CommentService {

	CommentDTO createComment(Integer postid,CommentDTO cDTO);
	void deleteComment(Integer commentId);
}
