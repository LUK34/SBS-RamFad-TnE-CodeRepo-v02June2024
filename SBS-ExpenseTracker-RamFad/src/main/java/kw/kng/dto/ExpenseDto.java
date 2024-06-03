package kw.kng.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
		description="Expense DTO(Data Transfer Object) to transfer the data between CLIENT and SERVER"
)
@Data
@AllArgsConstructor
public class ExpenseDto 
{
	Long id;
	BigDecimal amount;
	LocalDate expenseDate;
	CategoryDto categoryDto;
}
