package com.BlogApp.Service;

import java.util.List;
import com.BlogApp.Payloads.CategoryDTO;

public interface CategoryService {

	public CategoryDTO createCategory(CategoryDTO categorydto);
	public List<CategoryDTO> showAllCategory();
	public CategoryDTO getCategoryById(Integer categoryId);
	public CategoryDTO updateCategory(CategoryDTO categorydto, Integer categoryId);
	public void deleteCategory(Integer categoryId);
	public void deleteAllCategory();
}
