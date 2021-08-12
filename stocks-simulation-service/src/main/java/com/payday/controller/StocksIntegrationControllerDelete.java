package com.payday.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payday.client.StocksFeignClient;
import com.payday.dto.StocksPriceDto;
import com.payday.observer.KafkaStreamStocksObserver;

@RestController
@RequestMapping("stocks-simulation")
public class StocksIntegrationControllerDelete {

	@Autowired
	private StocksFeignClient stocksFeignClient;
	@Autowired
	private KafkaStreamStocksObserver streamPriceObserver;
	
	@GetMapping("/healthcheck/{sample}")
	public ResponseEntity<String> healthcheck(@PathVariable String sample) {
		return ResponseEntity.ok(sample + " from Stocks Simulation");
	}
	
	
	@GetMapping("/mq/{message}")
	public ResponseEntity<StocksPriceDto> putToQuue(@PathVariable String message) {
		StocksPriceDto stockPrice = new StocksPriceDto("ASEL", message, new BigDecimal(15), LocalDateTime.now());
		streamPriceObserver.update(stockPrice);
        return ResponseEntity.ok(stockPrice);
    }
}
