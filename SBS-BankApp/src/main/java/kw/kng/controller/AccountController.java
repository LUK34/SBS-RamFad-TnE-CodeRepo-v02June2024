package kw.kng.controller;

import java.util.List;
import java.util.Map;

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

import kw.kng.dto.AccountDto;
import kw.kng.dto.TransactionDto;
import kw.kng.dto.TransferFundDto;
import kw.kng.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController 
{
	private AccountService as;

	public AccountController(AccountService as) 
	{
		this.as = as;
	}
	
	//Add Account REST API
	//AccountDto is your Binding Class or Dto class which is responsible for binding the data to the entity
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
	{
		return new ResponseEntity<>(as.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	//Get Account details by the account id
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
	{
		AccountDto adt= as.getAccountById(id);
		return ResponseEntity.ok(adt);
	}
	
	//Update the amount details via deposit amount
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
																				@RequestBody Map<String, Double> request)
	{
		Double amount = request.get("amount");
		AccountDto accountDto = as.depositAmount(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//Update the amount details via the withdraw amount
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
																					@RequestBody Map<String, Double> request)
	{
		Double amount = request.get("amount");
		AccountDto accountDto = as.withdrawAmount(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}
	
	//Get all the account details 
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts()
	{
		
	List<AccountDto> accounts =	as.getAllAccounts();
	return ResponseEntity.ok(accounts);
	}
	
	//Delete the account details by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id)
	{
		as.deletAccountById(id);
		return ResponseEntity.ok("Account with id: "+id+" is deleted sucessfully!!");
	}
	
	//Transfering the amount from one account to another account called here
	@PostMapping("/transfer")
	public ResponseEntity<String> transferFund(@RequestBody TransferFundDto transferFundDto)
	{
		as.transferFunds(transferFundDto);
		return ResponseEntity.ok("Transfer Successfull");
	}
	
	//Build Transaction REST API
	@GetMapping("/{id}/transactions")
	public ResponseEntity<List<TransactionDto>> fetchAccountTransactions(@PathVariable("id") Long accountId)
	{
		List<TransactionDto> transactions =as.getAccountTransactions(accountId);
		
		return ResponseEntity.ok(transactions);
	}
	
	
}
