package com.payday.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payday.dto.StocksDto;
import com.payday.exceptions.PaydayServiceException;
import com.payday.mapper.StocksMapper;
import com.payday.model.Stocks;
import com.payday.repository.StocksRepository;
import com.payday.service.StocksService;

@Service
public class StocksServiceImpl implements StocksService {

	private Logger logger = LoggerFactory.getLogger(StocksServiceImpl.class);

	@Autowired
	private StocksRepository stocksRepository;
	@Autowired
	private StocksMapper stocksMapper;

	@Override
	public StocksDto getByStockId(Integer id) {
		Stocks stocks = stocksRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		return stocksMapper.mapToStocksDto(stocks);
	}

	@Override
	public StocksDto createStocks(StocksDto stocksDto) {
		if (stocksDto.getCode() == null || stocksDto.getCode().isEmpty()) {
			logger.error("Stocks code is null or empty");
			throw new PaydayServiceException("Stocks code cannot be null!!!");
		}
		Optional<Stocks> byCode = stocksRepository.findByCode(stocksDto.getCode());
		Stocks createdStocks = null;
		if (byCode.isPresent()) {
			Stocks stocks = byCode.get();
			stocks.setLastUpdateTime(stocksDto.getLastUpdateTime());
			stocks.setName(stocksDto.getName());
			stocks.setPrice(stocksDto.getPrice());
			
			createdStocks = stocksRepository.save(stocks);
		} else {
			createdStocks = stocksRepository.save(stocksMapper.mapToStocks(stocksDto));
		}
		return stocksMapper.mapToStocksDto(createdStocks);
	}

	@Override
	public StocksDto updateStocks(StocksDto stocksDto) {
		// if unique constraint is null or doesn't exist in db, throwse exception
		StocksDto byStockCode = getByStockCode(stocksDto.getCode());
		byStockCode.setLastUpdateTime(stocksDto.getLastUpdateTime());
		byStockCode.setName(stocksDto.getName());
		byStockCode.setPrice(stocksDto.getPrice());
		// update
		Stocks updated = stocksRepository.save(stocksMapper.mapToStocks(byStockCode));
		return stocksMapper.mapToStocksDto(updated);
	}

	@Override
	public StocksDto getByStockCode(String code) {
		Stocks stocks = stocksRepository.findByCode(code)
				.orElseThrow(() -> new PaydayServiceException("Stocks couldn't be found!!!"));
		return stocksMapper.mapToStocksDto(stocks);
	}

	@Override
	public List<StocksDto> getAllStocks() {
		return stocksRepository.findAll().stream().map((item) -> {
			return stocksMapper.mapToStocksDto(item);
		}).collect(Collectors.toList());
	}

}
