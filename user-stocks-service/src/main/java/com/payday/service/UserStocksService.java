package com.payday.service;

import java.util.List;

import com.payday.dto.UserStocksDto;
import com.payday.dto.UserStocksTransactionDto;

public interface UserStocksService {
	
	UserStocksDto createUserStocks(UserStocksDto userStocksDto);
	
	List<UserStocksDto> getUserStocks(String userId);
	
	List<UserStocksDto> getAll();
	
	UserStocksDto updateUserStocksByTransaction(UserStocksTransactionDto userTransaction);
}
