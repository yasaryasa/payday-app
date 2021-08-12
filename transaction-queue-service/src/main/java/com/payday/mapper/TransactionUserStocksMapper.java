package com.payday.mapper;
import org.mapstruct.Mapper;

import com.payday.dto.TransactionDto;
import com.payday.dto.UserStocksTransactionDto;

@Mapper(componentModel = "spring")
public interface TransactionUserStocksMapper {
   
	UserStocksTransactionDto mapToUserTransaction(TransactionDto transactionDto);
}