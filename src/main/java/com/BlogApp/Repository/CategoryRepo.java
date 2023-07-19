package com.BlogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApp.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
