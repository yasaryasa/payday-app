package com.payday.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payday.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

	Transaction findByUserId(Integer userId);
	
}
