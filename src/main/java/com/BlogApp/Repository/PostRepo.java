package com.BlogApp.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.Entities.Category;
import com.BlogApp.Entities.Post;
import com.BlogApp.Entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {

	List<Post> findByUser(User user);
	Page<Post> findByCategory(Category category,Pageable pg);
	
	List<Post> findByTitleContaining(String keywords);
}
