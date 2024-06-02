package kw.kng.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDto
{
	Long id;
	Long accountId;
	double amount;
	String transactionType;
	LocalDateTime timestamp;
}
