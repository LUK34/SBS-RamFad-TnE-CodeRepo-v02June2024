package kw.kng.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kw.kng.dto.CategoryDto;
import kw.kng.entites.Category;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.mapper.CategoryMapper;
import kw.kng.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService 
{

	private CategoryRepo crepo;

	public CategoryServiceImpl(CategoryRepo crepo)
	{
		this.crepo = crepo;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) 
	{
		//convert CategoryDto to Category entity
		Category category = CategoryMapper.mapToCategory(categoryDto);
		
		//save category object into database table - categories
		Category savedCategory = crepo.save(category);
		
		//convert savedCategory to CategoryDto
		return CategoryMapper.mapToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId)
	{
			Category category = crepo.findById(categoryId)
														.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: "+ categoryId+" not found in DB"));
		
			return CategoryMapper.mapToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() 
	{
		List<Category> categories = crepo.findAll();
		return	categories.stream()
		    					 	 .map((c) -> CategoryMapper.mapToCategoryDto(c))
									.collect(Collectors.toList());
	}

	@Override
	public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto)
	{
		
		Category category = crepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: "+ categoryId+" not found in DB"));

		
		category.setName(categoryDto.getName());
		Category updateCategory = crepo.save(category);
		
		return CategoryMapper.mapToCategoryDto(updateCategory);
	}

	@Override
	public void deleteCategoryById(Long categoryId)
	{
		Category category = crepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: "+ categoryId+" not found in DB"));
		
		crepo.delete(category);
		
	}
	
	
	
	
	
	
}
