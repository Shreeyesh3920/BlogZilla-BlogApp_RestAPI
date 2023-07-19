package com.BlogApp.Service;

import java.util.List;

import com.BlogApp.Entities.Post;
import com.BlogApp.Payloads.PostDTO;
import com.BlogApp.Payloads.PostResponse;

public interface PostService {
	 PostDTO createPost(PostDTO postdto, Integer userId, Integer categoryId);
	 PostDTO updatePost(PostDTO postdto,Integer postId);
	 void deletePost(Integer postId);
	 
	 //List<PostDTO> getAllPost(Integer pageNumber,Integer PageSize);
	 //{updated method in order to get contents defined in PostResponse Class}
	 PostResponse getAllPost(Integer pageNumber,Integer PageSize, String sortBy,String sortDirc);
	 
	 PostDTO getPostById(Integer postId);
	 
	 PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize);
	 List<PostDTO> getPostsByUser(Integer userId);
	 
	 List<PostDTO> searchPosts(String keywords);
	 
	
}
