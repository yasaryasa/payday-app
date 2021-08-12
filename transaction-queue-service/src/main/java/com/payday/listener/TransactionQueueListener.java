package com.payday.listener;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import com.payday.client.UserStocksFeignClient;
import com.payday.dto.TransactionDto;
import com.payday.dto.UserStocksDto;
import com.payday.dto.UserStocksTransactionDto;
import com.payday.mapper.TransactionUserStocksMapper;

@EnableBinding(Sink.class)
@Component
public class TransactionQueueListener {

	private Logger logger = LoggerFactory.getLogger(TransactionQueueListener.class);
	@Autowired
	private UserStocksFeignClient userStocksFeignClient;
	@Autowired
	private TransactionUserStocksMapper mapper;
	
	@StreamListener(Sink.INPUT)
    public void onNotification(TransactionDto dto){
		logger.info("———————————————————————————————————————————");
        logger.info("Transaction Bilgisi Alindi " + dto.getId() +" işlem gerçekleştiriliyor.");
        try {
        	//TODO Thread should sleep (1000 * 60 * 2) as requested in prod env. 
        	//For testing purposes, time will be 5000 miliseconds
            TimeUnit.MILLISECONDS.sleep(5000);
            UserStocksTransactionDto userTransaction = mapper.mapToUserTransaction(dto);
            UserStocksDto userStocks = userStocksFeignClient.updateUserStocks(userTransaction);
            //TODO user should get notified with socket or email.
            // Stock info should be send to notification queue
            logger.info("Transaction gerçekleştirildi, hookUrl : " + dto.getHookUrl() + " adresine response dönülecek.");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Transaction couldn't be processed", e);
        }
    }
	
}