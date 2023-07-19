package com.BlogApp.Service;

import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Entities.Category;

import com.BlogApp.Exceptions.CategoryNotFoundException;
import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.Payloads.CategoryDTO;

import com.BlogApp.Repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	public CategoryRepo categoryrepo;
	@Autowired
	public ModelMapper modelmapper;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categorydto) {
		Category cat=this.categoryDtoToCategory(categorydto);
		Category savedcat=this.categoryrepo.save(cat);
		return this.categoryToCategoryDTO(savedcat);
	}

	@Override
	public List<CategoryDTO> showAllCategory() {
		List<Category> categorylist=this.categoryrepo.findAll();
		List<CategoryDTO> categorydtolist=new LinkedList<>();
		for(Category c:categorylist) 
		{
			categorydtolist.add(categoryToCategoryDTO(c));
		}
		return categorydtolist;
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) {
		Category category=this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		return this.categoryToCategoryDTO(category);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categorydto, Integer categoryId) {
		Category category=this.categoryrepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		
		category.setCategoryTitle(categorydto.getCategoryTitle());
		category.setCategoryDescription(categorydto.getCategoryDescription());
		
		this.categoryrepo.save(category);
		
		return this.modelmapper.map(category, CategoryDTO.class);
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category=this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		this.categoryrepo.delete(category);
	}

	@Override
	public void deleteAllCategory() {
		List<Category> catlist=this.categoryrepo.findAll();
		if(catlist.isEmpty()) 
		{
			throw new CategoryNotFoundException("Categories Data Not Found to Delete");
		}
		else 
		{
			this.categoryrepo.deleteAll();
		}
	}

	//dtoToCategory
	public Category categoryDtoToCategory(CategoryDTO catdto) 
	{
		Category cat=this.modelmapper.map(catdto,Category.class );
		
		return cat;
	}
	//categoryToDTO
	public CategoryDTO categoryToCategoryDTO(Category cat) 
	{
		CategoryDTO catdto=this.modelmapper.map(cat, CategoryDTO.class);
		
		return catdto;
	}
	
}
