package com.payday.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payday.dto.UserStocksDto;
import com.payday.dto.UserStocksTransactionDto;
import com.payday.service.UserStocksService;

@RestController
@RequestMapping("user-stocks")
public class UserStocksController {

	@Autowired
	private UserStocksService userStocksService;
	
	@GetMapping("/healthcheck/{sample}")
	public ResponseEntity<String> healthcheck(@PathVariable String sample) {
		return ResponseEntity.ok(sample + " from User Stocks");
	}
	
	@PutMapping
	public ResponseEntity<UserStocksDto> createUserStocks(@RequestBody UserStocksDto userStocksDto) {
		UserStocksDto createdUserStocks = userStocksService.createUserStocks(userStocksDto);
		return ResponseEntity.ok(createdUserStocks);
	}
	
	@PostMapping("/update-transaction")
	public ResponseEntity<UserStocksDto> updateUserStocksTransaction(@RequestBody UserStocksTransactionDto userTransactionDto) {
		UserStocksDto updatedUserStocks = userStocksService.updateUserStocksByTransaction(userTransactionDto);
		return ResponseEntity.ok(updatedUserStocks);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<UserStocksDto>> getUserStocks(@PathVariable String userId) {
		List<UserStocksDto> userStocks = userStocksService.getUserStocks(userId);
		return ResponseEntity.ok(userStocks);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserStocksDto>> getAllUserStocks() {
		List<UserStocksDto> userStocks = userStocksService.getAll();
		return ResponseEntity.ok(userStocks);
	}
}
