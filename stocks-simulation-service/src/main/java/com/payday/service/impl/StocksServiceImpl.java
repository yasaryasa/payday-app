package com.payday.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payday.client.StocksFeignClient;
import com.payday.dto.StocksPriceDto;
import com.payday.service.StocksService;

@Service
public class StocksServiceImpl implements StocksService {

	@Autowired
	private StocksFeignClient stocksFeignClient;
	
	@Override
	public StocksPriceDto updateStocksPrice(StocksPriceDto stocksDto) {
		StocksPriceDto updated = stocksFeignClient.createStocks(stocksDto);
		return updated;
	}

}
