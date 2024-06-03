package kw.kng.mapper;

import kw.kng.dto.CategoryDto;
import kw.kng.dto.ExpenseDto;
import kw.kng.entites.Category;
import kw.kng.entites.Expense;

public class ExpenseMapper 
{
	//Map Entity class(Expense) to Binding Class(ExpenseDto)
	public static ExpenseDto mapToExpenseDto(Expense expense)
	{
		ExpenseDto expenseDto=new ExpenseDto(expense.getId(),
																				expense.getAmount(),
																				expense.getExpenseDate(),
																				new CategoryDto(
																												expense.getCategory().getId(),
																												expense.getCategory().getName()
																												)
																			   );
		
		return expenseDto;
	}
	
	//Map Binding class(ExpenseDto) class to Entity Class(Expense)
	public static Expense mapToExpense(ExpenseDto expenseDto)
	{
		Category category = new Category();
		category.setId(expenseDto.getCategoryDto().getId());
		
		Expense expense = new Expense(
						expenseDto.getId(),
						expenseDto.getAmount(),
						expenseDto.getExpenseDate(),
						category
				);
		
		return expense;
	}
	
	
	

}
