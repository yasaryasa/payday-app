package com.payday.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.payday.dto.BaseTransactionDto;
import com.payday.dto.TransactionDto;
import com.payday.dto.UserStocksDto;
import com.payday.service.UserStocksService;
import com.payday.utils.TransactionStatusEnum;

@Service
public class UserStocksServiceImpl implements UserStocksService {

	@Value("${base-url}")
	private String BASE_URL;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(UserStocksServiceImpl.class); 
	
	@Override
	public List<UserStocksDto> getUserStocks(String userId) {
		logger.info("BASE_URL : " + BASE_URL);
		UserStocksDto[] stocksDtos = restTemplate.getForObject(BASE_URL + "/user-stocks/" + userId, UserStocksDto[].class);
		return Arrays.asList(stocksDtos);
	}
	
	

	@Override
	public String getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}



	@Override
	public TransactionDto stockTransaction(BaseTransactionDto baseTransactionDto) {
		TransactionDto dto = new TransactionDto(baseTransactionDto);
		dto.setUserId(getLoggedInUser());
		dto.setHookUrl(BASE_URL);//for dummy data
		dto.setStatus(TransactionStatusEnum.REQUESTED);
		ResponseEntity<TransactionDto> created = restTemplate.postForEntity(BASE_URL + "/transaction/create", dto, TransactionDto.class);
		if (!HttpStatus.OK.equals(created.getStatusCode())) {
			throw new RuntimeException("Transaction failed!!! Please try again later.");
		} 
		return created.getBody();
	}




}

