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
import kw.kng.dto.ExpenseDto;
import kw.kng.service.ExpenseService;

@Tag(
		name="CRUD REST APIs for Expense Resource",
		description= "CRUD REST APIs for Expense Resource = Create Expense, Update Expense, Get Expense and Delete Expense"
)
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController 
{
	private ExpenseService es;

	//Constructor Injection
	public ExpenseController(ExpenseService es) 
	{
		this.es = es;
	}
	
	@Operation(
			summary="Create Expense REST API",
			description=" Create Expense REST API and saved Expense into Database"
	 )
	@ApiResponse(
			responseCode="201",
			description="HTTP STATUS 201 CREATED"
	 )
	@PostMapping
	public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto)
	{
		
		ExpenseDto savedExpense= es.createExpense(expenseDto);
		return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
	}
	
	
	@Operation(
			summary="GET Expense REST API",
			description=" GET Expense REST API to get a specific Expense by id from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@GetMapping("/{id}")
	public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable("id") Long expenseId)
	{
		ExpenseDto expense=es.getExpenseById(expenseId);
		return ResponseEntity.ok(expense);
	}
	
	@Operation(
			summary="GET All Expense REST API",
			description=" GET All Expense REST API to get all Expense from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	//Build get all expense REST API
	@GetMapping
	public ResponseEntity<List<ExpenseDto>> getAllExpenses()
	{
		
		List<ExpenseDto> expenses =	es.getAllExpenses();
		
		return ResponseEntity.ok(expenses);
				
	}
	
	@Operation(
			summary="Update Expense REST API",
			description=" Update Expense REST API to update Expense by id into Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	//Build update expense REST API
	@PutMapping("/{id}")
	public ResponseEntity<ExpenseDto> updateExpense(@PathVariable("id") Long expenseId, @RequestBody ExpenseDto expenseDto)
	{
		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.println("CONTROLLER LAYER: ");
		System.out.println("Expense Id:  "+expenseId);
		System.out.println("Expense Amount:  "+expenseDto.getAmount());
		System.out.println("Expense Date:  "+expenseDto.getExpenseDate());
		System.out.println("Expense Category Id:  "+expenseDto.getCategoryDto().getId());
		System.out.println("--------------------------------------------------------------------------------------------------");
		
		ExpenseDto updateExpense = es.updateExpenses(expenseId, expenseDto);
		
		return ResponseEntity.ok(updateExpense);
	}
	
	@Operation(
			summary="Delete Expense REST API",
			description=" Delete Expense REST API to delete Expense by id  into Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	//Build delete expense REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteExpense(@PathVariable("id") Long expenseId)
	{
		es.deleteExpense(expenseId);
		return ResponseEntity.ok("Expense with id: "+expenseId+ " deleted from database successfully!!");
	}
	

}
















