package kw.kng.mapper;

import kw.kng.dto.AccountDto;
import kw.kng.entities.Account;

public class AccountMapper 
{
	//This will map AccountDto (Binding Class) to Account (Entity class)	-> 	BINDING CLASS  --->  ENTITY CLASS
	public static Account mapToAccount(AccountDto accountDto)
	{
		Account account= new Account(accountDto.getId(),
															  accountDto.getAccountHolderName(),
															  accountDto.getBalance());
		
		return account;			
	}
	
	
	
	//This will map Account (Entity class) to AccountDo (Binding Class)	-> 	ENTITY CLASS  --->  BINDING CLASS
	public static AccountDto mapToAccountDto(Account account)
	{
			AccountDto accountDto= new AccountDto(	account.getId(),
																					account.getAccountHolderName(),
																					account.getBalance());
			
			return accountDto;
					
					
	}
	
	

}
