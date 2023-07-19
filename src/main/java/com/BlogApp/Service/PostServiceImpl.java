package com.BlogApp.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BlogApp.Entities.Post;
import com.BlogApp.Payloads.ApiResponse;
import com.BlogApp.Payloads.PostDTO;
import com.BlogApp.Payloads.PostResponse;
import com.BlogApp.Repository.CategoryRepo;
import com.BlogApp.Repository.PostRepo;
import com.BlogApp.Repository.UserRepo;
import com.BlogApp.Entities.*;
import com.BlogApp.Exceptions.*;


@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postrepo;
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private CategoryRepo categoryrepo;
	
	@Override
	public PostDTO createPost(PostDTO postdto,Integer userId, Integer categoryId) {
		
		User user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
		Category catergory=this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","category Id",categoryId));
		
		Post post=this.modelmapper.map(postdto,Post.class);
		//post.setTitle(postdto.getTitle());
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(catergory);
		post.setUser(user);
		
		
		Post savedPost=this.postrepo.save(post);
		return this.modelmapper.map(savedPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postdto, Integer postId) {
		
		Post post=this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostID",postId));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());
		post.setAddedDate(new Date());
		Post updatedPost=this.postrepo.save(post);
		return this.modelmapper.map(updatedPost,PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post=this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
		this.postrepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirc) {
		Sort sort=null;
		if(sortDirc.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		
		
		Pageable pg=PageRequest.of(pageNumber-1, pageSize,sort);
		Page<Post> pagePost=this.postrepo.findAll(pg);
		
		List<Post> posts=pagePost.getContent();
				
		List<PostDTO> pdto=new LinkedList<PostDTO>();
		for(Post p:posts) 
		{
			pdto.add(this.modelmapper.map(p, PostDTO.class));
		}
		PostResponse postresponse=new PostResponse();
		postresponse.setContent(pdto);
		postresponse.setPageNumber(pagePost.getNumber());
		postresponse.setPageSize(pagePost.getSize());
		postresponse.setTotalElements(pagePost.getNumberOfElements());
		postresponse.setTotalPages(pagePost.getTotalPages());
		postresponse.setLastPage(pagePost.isLast());
		return postresponse;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post=this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
		return this.modelmapper.map(post, PostDTO.class);
	}

	@Override
	
	public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
	    Category cat = this.categoryrepo.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
	    
	    Pageable pg = PageRequest.of(pageNumber, pageSize);
	    Page<Post> pagePost = this.postrepo.findByCategory(cat, pg);  // Filter posts by category
	    
	    List<PostDTO> pdtos = new LinkedList<>();
	    for (Post p : pagePost.getContent()) {
	        pdtos.add(this.modelmapper.map(p, PostDTO.class));
	    }
	    
	    PostResponse postResponse = new PostResponse();
	    postResponse.setContent(pdtos);
	    postResponse.setPageNumber(pagePost.getNumber());
	    postResponse.setPageSize(pagePost.getSize());
	    postResponse.setTotalElements(pagePost.getNumberOfElements());
	    postResponse.setTotalPages(pagePost.getTotalPages());
	    postResponse.setLastPage(pagePost.isLast());
	    
	    return postResponse;
	}

	@Override
	public List<PostDTO> getPostsByUser(Integer userId) {
		User user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		List<Post> posts=this.postrepo.findByUser(user);
		List<PostDTO> pdtos=new LinkedList<>();
		for(Post p:posts) 
		{
			pdtos.add(this.modelmapper.map(p, PostDTO.class));
		}
		return pdtos;
	}

	@Override
	public List<PostDTO> searchPosts(String keywords) {
		
		List<Post> searchedposts=this.postrepo.findByTitleContaining(keywords);
		List<PostDTO> spostDto=new LinkedList<>();
		for(Post p:searchedposts) 
		{
			spostDto.add(this.modelmapper.map(p, PostDTO.class));
		}
		
		return spostDto;
	}
	
}
