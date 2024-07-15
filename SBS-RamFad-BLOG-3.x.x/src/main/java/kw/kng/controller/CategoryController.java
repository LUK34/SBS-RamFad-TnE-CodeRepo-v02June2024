package kw.kng.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kw.kng.dto.CategoryDto;
import kw.kng.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController 
{
	private CategoryService cs;

	public CategoryController(CategoryService cs) 
	{
		this.cs = cs;
	}
	
	//Create a category -SINGLE
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/single")
	public ResponseEntity<CategoryDto> createCategorySingle(@RequestBody @Valid CategoryDto categoryDto)
	{
		CategoryDto savedCategory = cs.createCategorySingle(categoryDto);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
	
	//Create a category -MULTIPLE
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/multiple")
	public ResponseEntity<List<CategoryDto>>createCategoryMultiple(@RequestBody @Valid List< @Valid CategoryDto> categoryDto)
	{
		List<CategoryDto> savedCategoryList = cs.createCategoryMultiple(categoryDto);
		return new ResponseEntity<>(savedCategoryList, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryid)
	{
		CategoryDto savedCategory = cs.getCategoryByid(categoryid);
		return ResponseEntity.ok(savedCategory);
	}
	
	@GetMapping("/category-list")
	public ResponseEntity<List<CategoryDto>> getAllCategoryList()
	{
		List<CategoryDto> categoryList = cs.getCategoryListAll();
		return ResponseEntity.ok(categoryList);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto,@PathVariable("id") Long categoryid)
	{
		CategoryDto updatedCategory =	cs.updateCategory(categoryDto, categoryid);
		
		return ResponseEntity.ok(updatedCategory);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long categoryid)
	{
		cs.deleteCategoryById(categoryid);
		return ResponseEntity.ok("Category with id: "+categoryid+"  is deleted from DB !!!");
	}
	

}
