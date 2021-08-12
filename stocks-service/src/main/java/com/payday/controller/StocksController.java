package com.payday.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payday.dto.StocksDto;
import com.payday.service.StocksService;

@RestController
@RequestMapping("stocks")
public class StocksController {

	@Autowired
	private StocksService stocksService;
	
	@GetMapping("/healthcheck/{sample}")
	public ResponseEntity<String> healthcheck(@PathVariable String sample) {
		return ResponseEntity.ok(sample + " from Stocks");
	}
	
	@PostMapping("/create")
    public ResponseEntity<StocksDto> createStocks(@RequestBody StocksDto stocksDto) {
		StocksDto stocks = stocksService.createStocks(stocksDto);
        return ResponseEntity.ok(stocks);
    }
	
	@PostMapping("/update")
    public ResponseEntity<StocksDto> updateStocks(@RequestBody StocksDto stocksDto) {
		StocksDto stocks = stocksService.updateStocks(stocksDto);
        return ResponseEntity.ok(stocks);
    }
	
	@GetMapping("/{stocksId}")
    public ResponseEntity<StocksDto> getStocksById(@PathVariable Integer stocksId) {
		StocksDto stocksDto = stocksService.getByStockId(stocksId);
        return ResponseEntity.ok(stocksDto);
    }
	
	@GetMapping("/code/{stocksCode}")
    public ResponseEntity<StocksDto> getStocksByCode(@PathVariable("stocksCode") String stocksCode) {
		StocksDto stocksDto = stocksService.getByStockCode(stocksCode);
        return ResponseEntity.ok(stocksDto);
    }
	
	@GetMapping("/")
    public ResponseEntity<List<StocksDto>> getAllStocks() {
		List<StocksDto> stocksList = stocksService.getAllStocks();
        return ResponseEntity.ok(stocksList);
    }
}
