package com.payday.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payday.dto.UserStocksDto;
import com.payday.dto.UserStocksTransactionDto;
import com.payday.exceptions.PaydayServiceException;
import com.payday.mapper.UserStocksMapper;
import com.payday.model.UserStocks;
import com.payday.repository.UserStocksRepo;
import com.payday.service.UserStocksService;
import com.payday.utils.TransactionTypeEnum;

@Service
public class UserStocksServiceImpl implements UserStocksService {

	@Autowired
	private UserStocksRepo userStocksRepo;
	@Autowired
	private UserStocksMapper userStocksMapper;

	@Override
	public UserStocksDto createUserStocks(UserStocksDto userStocksDto) {
		UserStocks userStocks = userStocksMapper.mapToUserStocks(userStocksDto);
		UserStocks persistedUserStocks = userStocksRepo.save(userStocks);
		return userStocksMapper.mapToUserStocksDto(persistedUserStocks);
	}

	@Override
	public List<UserStocksDto> getUserStocks(String userId) {
		List<UserStocksDto> dtoList = userStocksRepo.findByUserId(userId).stream().map((item) -> {
			return userStocksMapper.mapToUserStocksDto(item);
		}).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public List<UserStocksDto> getAll() {
		List<UserStocksDto> dtoList = userStocksRepo.findAll().stream().map((item) -> {
			return userStocksMapper.mapToUserStocksDto(item);
		}).collect(Collectors.toList());
		return dtoList;
	}

	@Transactional
	@Override
	public UserStocksDto updateUserStocksByTransaction(UserStocksTransactionDto userTransaction) {
		UserStocks userStock = userStocksRepo.findByUserIdAndStockCode(userTransaction.getUserId(),
				userTransaction.getStockCode());
		if (userStock == null || userStock.getId() == null) { // create new user stocks
			if (TransactionTypeEnum.SELL.equals(userTransaction.getTransactionType())) {
				throw new PaydayServiceException("User doesn't have this stocks!!!");
			}
			UserStocks s = new UserStocks();
			s.setAmount(userTransaction.getAmount());
			s.setPrice(userTransaction.getPrice());
			s.setStockCode(userTransaction.getStockCode());
			s.setStockName(userTransaction.getStockName());
			s.setUserId(userTransaction.getUserId());
			UserStocks savedStocks = userStocksRepo.save(s);
			return userStocksMapper.mapToUserStocksDto(savedStocks);
		} else {
			if (TransactionTypeEnum.BUY.equals(userTransaction.getTransactionType())) {
				userStock.setAmount(userStock.getAmount() + userTransaction.getAmount());
				userStock.setPrice(userTransaction.getPrice());
			} else if (TransactionTypeEnum.SELL.equals(userTransaction.getTransactionType())) {
				if (userTransaction.getAmount() > userStock.getAmount()) {
					throw new PaydayServiceException("Sell Amount cannot be greate then Holded Amount!!!");
				}
				userStock.setAmount(userStock.getAmount() - userTransaction.getAmount());
				userStock.setPrice(userTransaction.getPrice());
			}
		}
		UserStocks savedStocks = userStocksRepo.save(userStock);
		return userStocksMapper.mapToUserStocksDto(savedStocks);
	}

}
