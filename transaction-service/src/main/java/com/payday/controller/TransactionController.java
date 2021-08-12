package com.payday.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payday.dto.TransactionDto;
import com.payday.service.TransactionService;

@RestController
@RequestMapping("transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/healthcheck/{sample}")
	public ResponseEntity<String> healthcheck(@PathVariable String sample) {
		return ResponseEntity.ok(sample + " from User Transaction");
	}
	
	@PostMapping("/create")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto txDto) {
		TransactionDto createdTx = transactionService.createTransaction(txDto);
        return ResponseEntity.ok(createdTx);
    }
	
	@PostMapping("/update/{transactionId}")
    public ResponseEntity<TransactionDto> updateTransaction(@RequestBody TransactionDto txDto) {
		TransactionDto createdTx = transactionService.createTransaction(txDto);
        return ResponseEntity.ok(createdTx);
    }
	
}
