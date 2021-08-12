package com.payday.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payday.dto.StocksPriceDto;


@FeignClient(name="stocks-service")
public interface StocksFeignClient {

	@RequestMapping("/stocks/sample/{sample}")
	String getSampelStock(@PathVariable String sample);
	
	@PostMapping("/stocks/create")
    StocksPriceDto createStocks(@RequestBody StocksPriceDto stocksDto);
	
}
