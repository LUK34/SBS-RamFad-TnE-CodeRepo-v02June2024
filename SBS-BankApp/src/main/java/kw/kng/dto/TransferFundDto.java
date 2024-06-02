package kw.kng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferFundDto 
{
	Long fromAccountId;
	Long toAccountId;
	double amount;
}
