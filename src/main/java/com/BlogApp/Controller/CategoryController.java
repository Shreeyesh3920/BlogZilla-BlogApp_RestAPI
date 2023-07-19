package com.BlogApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Payloads.ApiResponse;
import com.BlogApp.Payloads.CategoryDTO;
import com.BlogApp.Service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")

public class CategoryController {
	
	@Autowired
	private CategoryService categoryservice;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody  CategoryDTO categorydto)
	{
		CategoryDTO createdCategorydto=this.categoryservice.createCategory(categorydto);
		return new ResponseEntity<CategoryDTO>(createdCategorydto,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/showcategories")
	public ResponseEntity<List<CategoryDTO>> getCategories()
	{
		List<CategoryDTO> categoryDtoList=this.categoryservice.showAllCategory();
		return new ResponseEntity<List<CategoryDTO>>(categoryDtoList,HttpStatus.FOUND);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer catId)
	{
		CategoryDTO catdto=this.categoryservice.getCategoryById(catId);
		
		return new ResponseEntity<CategoryDTO>(catdto,HttpStatus.FOUND);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDTO> updateCategoryById(@RequestBody @Valid CategoryDTO categorydto, @PathVariable Integer catId)
	{
		CategoryDTO catDto=this.categoryservice.updateCategory(categorydto, catId);
		return new ResponseEntity<CategoryDTO>(catDto,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer catId)
	{
		this.categoryservice.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Sucessfully",true),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteall")
	public ResponseEntity<ApiResponse> deleteall()
	{
		this.categoryservice.deleteAllCategory();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All Categories Deleted Sucessfully",true),HttpStatus.OK);
	}
	
}
