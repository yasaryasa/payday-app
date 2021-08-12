package com.payday.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.payday.dto.StocksDto;
import com.payday.model.Stocks;

@Mapper(componentModel = "spring")
public interface StocksMapper {
   
	StocksDto mapToStocksDto(Stocks stocks);
	
	Stocks mapToStocks(StocksDto stocksDto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "code", ignore = true)
	StocksDto ignoreUniquesMap(StocksDto stocksDto);
}