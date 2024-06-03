package kw.kng.service;

import java.util.List;

import kw.kng.dto.ExpenseDto;

public interface ExpenseService
{
	ExpenseDto createExpense(ExpenseDto expenseDto);
	ExpenseDto getExpenseById(Long expenseId);
	List<ExpenseDto> getAllExpenses();
	ExpenseDto updateExpenses(Long expenseId, ExpenseDto expenseDto);
	void deleteExpense(Long expenseId);
}
