package kw.kng.controller;

import java.util.List;

import javax.validation.Valid;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kw.kng.dto.CategoryDto;
import kw.kng.service.CategoryService;

@Tag(
		name="BLOG REST APIs for Category Resource",
		description= "BLOG REST APIs for Category Resource = Create Category, Update Catgeory, Get Category and Delete Category"
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

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Create a category -SINGLE
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/single")
	@Operation(
			summary="Create Single Category REST API",
			description=" Create Single Category REST API and saved category into Database"
	 )
	@ApiResponse(
			responseCode="201",
			description="HTTP STATUS 201 CREATED"
	 )
	public ResponseEntity<CategoryDto> createCategorySingle(@RequestBody @Valid CategoryDto categoryDto)
	{
		CategoryDto savedCategory = cs.createCategorySingle(categoryDto);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Create a category -MULTIPLE
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/multiple")
	@Operation(
			summary="Create Multiple Category REST API",
			description=" Create Multiple Category REST API and saved category into Database"
	 )
	@ApiResponse(
			responseCode="201",
			description="HTTP STATUS 201 CREATED"
	 )
	public ResponseEntity<List<CategoryDto>>createCategoryMultiple(@RequestBody @Valid List< @Valid CategoryDto> categoryDto)
	{
		List<CategoryDto> savedCategoryList = cs.createCategoryMultiple(categoryDto);
		return new ResponseEntity<>(savedCategoryList, HttpStatus.CREATED);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Operation(
			summary="GET Category by id REST API",
			description="GET Category by id REST API to get a specific category by id from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryid)
	{
		CategoryDto savedCategory = cs.getCategoryByid(categoryid);
		return ResponseEntity.ok(savedCategory);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Operation(
			summary="GET Category List REST API",
			description=" GET Category List REST API to get a specific category by id from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@GetMapping("/category-list")
	public ResponseEntity<List<CategoryDto>> getAllCategoryList()
	{
		List<CategoryDto> categoryList = cs.getCategoryListAll();
		return ResponseEntity.ok(categoryList);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="UPDATE Category by id REST API",
			description=" UPDATE Category by id REST API to get a specific category by id from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto,@PathVariable("id") Long categoryid)
	{
		CategoryDto updatedCategory =	cs.updateCategory(categoryDto, categoryid);
		
		return ResponseEntity.ok(updatedCategory);
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="DELETE Category by id REST API",
			description="DELETE Category by id REST API to get a specific category by id from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long categoryid)
	{
		cs.deleteCategoryById(categoryid);
		return ResponseEntity.ok("Category with id: "+categoryid+"  is deleted from DB !!!");
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

}
