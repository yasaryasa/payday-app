package com.payday.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.payday.dto.TransactionDto;

@EnableBinding(Sink.class)
public class StockTransactionService {

	private Logger logger = LoggerFactory.getLogger(StockTransactionService.class);
	
	@StreamListener(Sink.INPUT)
    public void onNotification(TransactionDto dto){
		logger.info("———————————————————————————————————————————");
        logger.info("Transaction Bilgisi Alindi " + dto.getId() +" işlem gerçekleştiriliyor.");
        try {
        	//TODO Thread should sleep (1000 * 60 * 2) as requested in prod env. 
        	//For testing purposes, time will be 5000 miliseconds
            TimeUnit.MILLISECONDS.sleep(5000);
            logger.info("Transaction gerçekleştirildi, hookUrl : " + dto.getHookUrl() + " adresine response dönülecek.");
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("Transaction couldn't be processed", e);
        }
    }
	
}
