package com.payday.mapper;
import org.mapstruct.Mapper;

import com.payday.dto.TransactionDto;
import com.payday.model.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
   
	TransactionDto mapToTransactionDto(Transaction transaction);
	
	Transaction mapTopTransaction(TransactionDto transactionDto);
}