package kw.kng.mapper;

import kw.kng.dto.CategoryDto;
import kw.kng.entites.Category;

public class CategoryMapper 
{
	//Map Binding Class (CategoryDto) to Entity Class (Category)
	public static Category mapToCategory(CategoryDto categoryDto) 
	{
		Category category= new Category(categoryDto.getId(),
																  categoryDto.getName());
		
		return category;
	}

	//Map Entity class (Category) to Binding Class (CategoryDto)
	public static CategoryDto mapToCategoryDto(Category category)
	{
		CategoryDto categoryDto= new CategoryDto(category.getId(),
																					category.getName());
		return categoryDto;
	}
	
	
}
