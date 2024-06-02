package kw.kng.mapper;

import kw.kng.dto.TransactionDto;
import kw.kng.entities.Transaction;

public class TransactionMapper 
{
	//Transfer data from Transaction ENTITY to TransactioDto BINDING CLASS
	
	public static TransactionDto mapToTransactiontDto(Transaction transaction)
	{
		TransactionDto transactionDto= new TransactionDto(
																transaction.getId(),
																transaction.getAccountId(),
																transaction.getAmount(),
																transaction.getTransactionType(),
																transaction.getTimestamp()
																);
		return transactionDto;
	}

}
