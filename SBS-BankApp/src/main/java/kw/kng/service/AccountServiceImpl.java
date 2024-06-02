package kw.kng.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kw.kng.dto.AccountDto;
import kw.kng.dto.TransactionDto;
import kw.kng.dto.TransferFundDto;
import kw.kng.entities.Account;
import kw.kng.entities.Transaction;
import kw.kng.exceptions.AccountException;
import kw.kng.exceptions.InsufficentAmountException;
import kw.kng.mapper.AccountMapper;
import kw.kng.mapper.TransactionMapper;
import kw.kng.repo.AccountRepo;
import kw.kng.repo.TransactionRepo;

@Service
public class AccountServiceImpl implements AccountService 
{

	private AccountRepo arepo;
	private TransactionRepo trepo;
	
	private static final String TRANSACTION_TYPE_DEPOSIT="DEPOSIT";
	private static final String TRANSACTION_TYPE_WITHDRAW="WITHDRAW";
	private static final String TRANSACTION_TYPE_TRANSFER="TRANSFER";
	
	//Constructor Injection
	public AccountServiceImpl(AccountRepo arepo,TransactionRepo trepo) 
	{
		this.arepo = arepo;
		this.trepo=trepo;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) 
	{
		//Map the contents from Binding class to Entity Class
		Account account= AccountMapper.mapToAccount(accountDto);
		Account saveAccount=arepo.save(account);//save the data into persistence layer using jpa
		
		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) 
	{
		Account account=arepo.findById(id)
												.orElseThrow(() -> new AccountException("Account with id "+ id +" does not exist in DB"));
	
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto depositAmount(Long id, double amount) 
	{
		Account account=arepo.findById(id)
				.orElseThrow(() -> new AccountException("Account with id "+ id +" does not exist in DB"));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = arepo.save(account);
		
		//Log the Transaction Details
		Transaction transaction = new Transaction();
		transaction.setAccountId(id);
		transaction.setAmount(amount);
		transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
		transaction.setTimestamp(LocalDateTime.now());
		
		//Save the Transaction Details
		trepo.save(transaction); //u need to make sure that you import the correct class.
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdrawAmount(Long id, double amount)
	{
		Account account=arepo.findById(id)
				.orElseThrow(() -> new AccountException("Account with id "+ id +" does not exist in DB"));
		
		
		if(account.getBalance() < amount)
		{
			throw new InsufficentAmountException("There is insufficent amount in the bank account for withdrawl.BALANCE Amt: "+account.getBalance()+" , WITHDRAW Amt: "+amount+" Account id=  "+id);
		}
		
		double total= account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount= arepo.save(account);
		
		//Log the Transaction Details
		Transaction transaction = new Transaction();
		transaction.setAccountId(id);
		transaction.setAmount(amount);
		transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
		transaction.setTimestamp(LocalDateTime.now());
		
		//Save the Transaction Details
		trepo.save(transaction); //u need to make sure that you import the correct class.
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() 
	{
		List<Account> accounts = arepo.findAll();
		return accounts.stream()
					  .map((account) -> AccountMapper.mapToAccountDto(account))
					  .collect(Collectors.toList());
	}

	@Override
	public void deletAccountById(Long id)
	{
		Account account=arepo.findById(id)
				.orElseThrow(() -> new AccountException("Account with id "+ id +" does not exist in DB"));
		
		arepo.deleteById(id);
		
	}

	@Override
	public void transferFunds(TransferFundDto transferFundDto) 
	{
			//Retrieve the ACCOUNT ID FROM which we send the amount.
			Account fromAccount= arepo.findById(transferFundDto.getFromAccountId())
					  										 .orElseThrow(() -> new AccountException("Account with id "+ transferFundDto.getFromAccountId() +" does not exist in DB"));
	
			
			//Retrieve the ACCOUNT ID TO which we send the amount.
			Account toAccount= arepo.findById(transferFundDto.getToAccountId())
						 								 .orElseThrow(() -> new AccountException("Account with id "+ transferFundDto.getToAccountId() +" does not exist in DB"));

			//If the FROM Account have INSUFFICENT BALANCE compared to TRANSFER AMOUNT
			if(fromAccount.getBalance() < transferFundDto.getAmount())
			{
				throw new InsufficentAmountException("There is Insufficent Amount to transfer -> Current Account(FROM)= "+fromAccount.getBalance() +" <  Transfer Amount= "+transferFundDto.getAmount());
			}
			
			
			//DEBIT the amount from `fromAccount` object
			//Basically we are transferring the money from `fromAccount`(DEBIT) to `toAccount`(CREDIT)
			fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.getAmount());
			
			
			//CREDIT the amount from `toAccount` object
			//Basically we are transferring the money from `fromAccount`(DEBIT) to `toAccount`(CREDIT)
			toAccount.setBalance(toAccount.getBalance() + transferFundDto.getAmount());
			
			
			//Log the Transaction Details
			Transaction transaction = new Transaction();
			transaction.setAccountId(transferFundDto.getFromAccountId());
			transaction.setAmount(transferFundDto.getAmount());
			transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
			transaction.setTimestamp(LocalDateTime.now());
			
			
			//Save the Transaction Details
			trepo.save(transaction); //u need to make sure that you import the correct class.
			
			
			//SAVE the details in persistence layer
			arepo.save(fromAccount);
			arepo.save(toAccount);
			
	}

	@Override
	public List<TransactionDto> getAccountTransactions(Long accountId) 
	{
		List<Transaction> transactions = trepo.findByAccountIdOrderByTimestampDesc(accountId);
		return transactions.stream()
										.map((t) ->TransactionMapper.mapToTransactiontDto(t) )
										.collect(Collectors.toList());
	}
	
	

}

/*
 Exception Handling:
 -----------------------------
 
 1. Handling exceptions in Spring Boot Rest APIs is typically done using the @ControllerAdvice and @ExceptionHandler annotations.
 
 2. @ControllerAdvice enables global exception handling for controllers.
 
 3. @ExceptionHandler defines methods to handle specific exceptions withing a controller or globally with @ControllerAdvice.
 
 Development Steps:
 ----------------------------
 1. Create custom exception named AccountException.
 
 2.Using AccountException.
 
 3. Create ErrorDetails class to hold error response.
 
 4. Create GlobalExceptionHandler to handle specific exception as well as generic exceptions.
 
 
 */



























