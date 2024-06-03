package kw.kng.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kw.kng.dto.ExpenseDto;
import kw.kng.entites.Category;
import kw.kng.entites.Expense;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.mapper.ExpenseMapper;
import kw.kng.repo.CategoryRepo;
import kw.kng.repo.ExpenseRepo;

@Service
public class ExpenseServiceImpl implements ExpenseService 
{
	private ExpenseRepo erepo;
	private CategoryRepo crepo;
	
	public ExpenseServiceImpl(ExpenseRepo erepo, CategoryRepo crepo) 
	{
		this.erepo = erepo;
		this.crepo=crepo;
	}

	@Override
	public ExpenseDto createExpense(ExpenseDto expenseDto) {
		
		//Convert ExpenseDto (Binding class) to Expense (Entity class)
		Expense expense = ExpenseMapper.mapToExpense(expenseDto);
		
		//Saave expense entity to Database
		Expense saveExpense = erepo.save(expense);
		
		//Convert the saved expense entity to ExpenseDto
		return ExpenseMapper.mapToExpenseDto(saveExpense);
		
	}

	@Override
	public ExpenseDto getExpenseById(Long expenseId) 
	{
		//Get expense entity from the database using expense id
		Expense expenses = erepo.findById(expenseId)
				  									.orElseThrow(() -> new ResourceNotFoundException("Expense with id: "+expenseId+" not found in DB"));
		
		//convert expense entity to ExpenseDto
		return ExpenseMapper.mapToExpenseDto(expenses);
		
	}

	@Override
	public List<ExpenseDto> getAllExpenses() 
	{
		List<Expense> expenses= erepo.findAll();
		
		return expenses.stream()
									.map((e) -> ExpenseMapper.mapToExpenseDto(e))
									.collect(Collectors.toList());
	}

	@Override
	public ExpenseDto updateExpenses(Long expenseId, ExpenseDto expenseDto) 
	{
				//Get expense entity from the database using expense id
				Expense expenses = erepo.findById(expenseId)
						  									.orElseThrow(() -> new ResourceNotFoundException("Expense with id: "+expenseId+" not found in DB"));
				
				System.out.println("--------------------------------------------------------------------------------------------------");
				System.out.println("SERVICE LAYER: ");
				System.out.println("Expense Id:  "+expenseId);
				System.out.println("Expense Amount:  "+expenseDto.getAmount());
				System.out.println("Expense Date:  "+expenseDto.getExpenseDate());
				System.out.println("Expense Category Id:  "+expenseDto.getCategoryDto().getId());
				System.out.println("--------------------------------------------------------------------------------------------------");
				
				
				//Update expense details
				expenses.setAmount(expenseDto.getAmount());
				expenses.setExpenseDate(expenseDto.getExpenseDate());
				
				//Before updating the category of the expense. We need to make sure that that the category of expense is not null
				if(expenseDto.getCategoryDto() != null)
				{
					//get the category entity by id
					Category category = crepo.findById(expenseDto.getCategoryDto().getId())
							 .orElseThrow(() -> new ResourceNotFoundException("Category id: " + expenseDto.getCategoryDto().getId() + "  assigned for this expense is not found in DB"));
				
					expenses.setCategory(category);
				}
				
				//Update expense entity into database
				Expense updatedExpense = erepo.save(expenses);
				
				//convert expense entity to ExpenseDto
				return ExpenseMapper.mapToExpenseDto(updatedExpense);
	}

	@Override
	public void deleteExpense(Long expenseId) 
	{
		
		Expense expense = erepo.findById(expenseId)
												  .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: "+ expenseId));
		
		erepo.delete(expense);
	}
	
	

	
	
}
