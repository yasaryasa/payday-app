package com.payday.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payday.dto.UserStocksDto;
import com.payday.dto.UserStocksTransactionDto;


@FeignClient(name="user-stocks-service")
public interface UserStocksFeignClient {

	@PutMapping("/user-stocks/")
	UserStocksDto createUserStocks(@RequestBody UserStocksDto userStocksDto);
	
	@RequestMapping("/sample/{sample}")
	String getSampelStock(@PathVariable String sample);
	
	@PostMapping("/user-stocks/update-transaction")
	UserStocksDto updateUserStocks(@RequestBody UserStocksTransactionDto userStocksTransactionDto);
}
