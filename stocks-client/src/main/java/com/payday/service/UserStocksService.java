package com.payday.service;

import java.util.List;

import com.payday.dto.BaseTransactionDto;
import com.payday.dto.TransactionDto;
import com.payday.dto.UserStocksDto;

public interface UserStocksService {

	String getLoggedInUser();

	/**
	 * Gateway üzerinden kullanıcının hisse bilgilerini getirir
	 * 
	 * @param userId
	 * @return
	 */
	List<UserStocksDto> getUserStocks(String userId);

	TransactionDto stockTransaction(BaseTransactionDto baseTransactionDto);
}
