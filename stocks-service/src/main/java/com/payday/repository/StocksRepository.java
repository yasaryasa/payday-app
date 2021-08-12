package com.payday.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payday.model.Stocks;

public interface StocksRepository extends JpaRepository<Stocks, Integer> {
	
	Optional<Stocks> findByCode(String code);
}
