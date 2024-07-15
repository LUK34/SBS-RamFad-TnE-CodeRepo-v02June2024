package kw.kng.service;



import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.dto.CategoryDto;
import kw.kng.entites.Category;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService 
{
	private CategoryRepo crepo;
	private ModelMapper modelMapper;
	
	public CategoryServiceImpl(CategoryRepo crepo, ModelMapper modelMapper) 
	{
		this.crepo = crepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDto createCategorySingle(CategoryDto categoryDto) 
	{
		//Transfer DTO to Entity Class
		Category category = modelMapper.map(categoryDto, Category.class);

		//Save the category details in PERSISTENCE layer
		Category savedCategory=	crepo.save(category);
		
		//transfer ENTITY to DTO
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> createCategoryMultiple(List<CategoryDto> categoryDto)
	{
		//Transfer DTO to ENTITY
		List<Category> categoryList = categoryDto.stream().map(c ->modelMapper.map(c, Category.class)).collect(Collectors.toList());
		
		//Save the category details in PERSISTENCE Layer
		List<Category> savedCategory = crepo.saveAll(categoryList);
	
		//Transfer ENTITY to DTO
		return savedCategory.stream().map(c ->modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryByid(Long categoryId) 
	{
		//Find the specific category from persistence using ID.If not found throw ResourceNotFoundException
		Category category = crepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with id: "+categoryId+"  not found in DB !!!"));
	
		//Transfer ENTITY to DTO
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategoryListAll() 
	{
		//Find all the category from PERSISTENCE
		List<Category> categoryList = crepo.findAll();
		
		//Transfer ENTITY to DTO
		return categoryList.stream().map(c -> modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) 
	{
		Category category = crepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with id: "+categoryId+" not found in DB!!!"));
		
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		
		Category updatedCategory = crepo.save(category);
	
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategoryById(Long categoryid)
	{
		Category category = crepo.findById(categoryid).orElseThrow(() -> new ResourceNotFoundException("Category with id: "+categoryid+" not found in DB !!!"));
		crepo.delete(category);
	}

	
	
	
	
}
