package kw.kng.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kw.kng.dto.CategoryDto;
import kw.kng.service.CategoryService;

@Tag(
			name="CRUD REST APIs for Category Resource",
			description= "CRUD REST APIs for Category Resource = Create Category, Update Catgeory, Get Category and Delete Category"
)
@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
	private CategoryService cs;

	public CategoryController(CategoryService cs) 
	{
		this.cs = cs;
	}

	@Operation(
			summary="Create Category REST API",
			description=" Create Category REST API and saved category into Database"
	 )
	@ApiResponse(
			responseCode="201",
			description="HTTP STATUS 201 CREATED"
	 )
	//Build create category REST API
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
		CategoryDto category = cs.createCategory(categoryDto);
		
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}
	
	
	@Operation(
			summary="GET Category REST API",
			description=" GET Category REST API to get a specific category by id from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	//Build Get Category by id REST API
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryByiD(@PathVariable("id") Long categoryId)
	{
		CategoryDto category = cs.getCategoryById(categoryId);
		return ResponseEntity.ok(category);
	}
	
	
	@Operation(
			summary="GET All Category REST API",
			description=" GET All Category REST API to get all category from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	//Build Get All Categories REST API
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> categories = cs.getAllCategories();
		return ResponseEntity.ok(categories);
	}
	
	
	@Operation(
			summary="Update Category REST API",
			description=" Update Category REST API to update category by id into Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	//Build update category REST API
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id")  Long categoryId,
																							  @RequestBody CategoryDto categoryDto)	
	{
		CategoryDto updateCategory = cs.updateCategory(categoryId, categoryDto);
		
		return ResponseEntity.ok(updateCategory);
	}
	
	
	@Operation(
			summary="Delete Category REST API",
			description=" Delete Category REST API to delete category by id  into Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId)
	{
		cs.deleteCategoryById(categoryId);
		return ResponseEntity.ok("Category with id: "+categoryId+" is deleted sucessfully!!!");
	}
	
	
}
