package kw.kng.service;

import java.util.List;

import kw.kng.dto.CategoryDto;

public interface CategoryService 
{
	CategoryDto createCategorySingle(CategoryDto categoryDto);
	List<CategoryDto> createCategoryMultiple(List<CategoryDto> categoryDto);
	CategoryDto getCategoryByid(Long categoryId);
	List<CategoryDto> getCategoryListAll();
	CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
	void deleteCategoryById(Long categoryid);
	
}
