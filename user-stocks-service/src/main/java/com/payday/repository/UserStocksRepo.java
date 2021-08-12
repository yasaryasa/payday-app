package com.payday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payday.model.UserStocks;

public interface UserStocksRepo extends JpaRepository<UserStocks, String>{

	List<UserStocks> findByUserId(String userId);
	
	UserStocks findByUserIdAndStockCode(String userId, String stockCode);
	
}
