package kw.kng.service;

import java.util.List;

import kw.kng.dto.AccountDto;
import kw.kng.dto.TransactionDto;
import kw.kng.dto.TransferFundDto;

public interface AccountService 
{
	AccountDto createAccount(AccountDto accountDto);
	AccountDto getAccountById(Long id);
	AccountDto depositAmount(Long id, double amount);
	AccountDto withdrawAmount(Long id, double amount);
	List<AccountDto> getAllAccounts();
	void deletAccountById(Long id);
	void transferFunds(TransferFundDto transferFundDto);
	List<TransactionDto> getAccountTransactions(Long accountId);
	
	
}



