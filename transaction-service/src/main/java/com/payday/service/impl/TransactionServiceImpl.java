package com.payday.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payday.dto.TransactionDto;
import com.payday.mapper.TransactionMapper;
import com.payday.model.Transaction;
import com.payday.repositories.TransactionRepo;
import com.payday.service.TransactionService;
import com.payday.service.TransactionQueueService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;
	@Autowired
	private TransactionQueueService transactionQueueService;
	@Autowired
	private TransactionMapper transactionMapper;
	
	@Transactional
	public TransactionDto createTransaction (TransactionDto transactionDto) {
		Transaction transaction = transactionMapper.mapTopTransaction(transactionDto);
		//create tx record
		Transaction persistedTransaction = transactionRepo.save(transaction);
		TransactionDto dto = transactionMapper.mapToTransactionDto(persistedTransaction);
		//send to mq
		transactionQueueService.sendToQueue(dto);
		return dto;
	}
}
