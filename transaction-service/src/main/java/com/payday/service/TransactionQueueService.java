package com.payday.service;

import com.payday.dto.TransactionDto;

public interface TransactionQueueService {

	void sendToQueue(TransactionDto transaction);
	
}
