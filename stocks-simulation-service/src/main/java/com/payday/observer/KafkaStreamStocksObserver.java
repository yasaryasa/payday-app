package com.payday.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
//import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.payday.dto.StocksPriceDto;

@Component
public class KafkaStreamStocksObserver implements StocksObserver {

	private Logger logger = LoggerFactory.getLogger(KafkaStreamStocksObserver.class);

	@Autowired
	private StreamBridge streamBridge;

	@Override
	public void update(StocksPriceDto stocksPrice) {
		// NY, AED borsalarına göre partition yapılabilir.
		// Örnek olması için bu şekilde ilerlendi
		Message<StocksPriceDto> message = MessageBuilder.withPayload(stocksPrice)
				.setHeader("partitionKey", stocksPrice.getCode()).build();
		streamBridge.send("prices-out-0", message);
		logger.info("{} sent to bus.", stocksPrice.getPrice());
	}
}
