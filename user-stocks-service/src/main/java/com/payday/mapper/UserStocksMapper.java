package com.payday.mapper;
import org.mapstruct.Mapper;

import com.payday.dto.UserStocksDto;
import com.payday.model.UserStocks;

@Mapper(componentModel = "spring")
public interface UserStocksMapper {
   
	UserStocksDto mapToUserStocksDto(UserStocks userStocks);
	
	UserStocks mapToUserStocks(UserStocksDto userStocksDto);
}