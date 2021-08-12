package com.payday.service;

import java.util.List;

import com.payday.dto.StocksDto;

public interface StocksService {

	StocksDto getByStockId(Integer id);
	
	StocksDto createStocks(StocksDto stocksDto);
	
	StocksDto getByStockCode(String code);

	List<StocksDto> getAllStocks();

	StocksDto updateStocks(StocksDto stocksDto);
}
