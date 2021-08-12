package com.payday.bus;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.payday.dto.StocksDto;

@Component
public class StocksPriceStream {

	Logger logger = LoggerFactory.getLogger(StocksPriceStream.class);
	
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

	
	
    @Bean
    public Consumer<Message<StocksDto>> prices() {
        return message -> {
        	StocksDto priceDto = message.getPayload();
            MessageHeaders messageHeaders = message.getHeaders();
            logger.info("PriceMessage with id {}, value '{}' and timestamp '{}' received from bus. topic: {}, partition: {}, offset: {}, deliveryAttempt: {}",
                    priceDto.getCode(), priceDto.getPrice(), priceDto.getPriceTime(),
                    messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
                    messageHeaders.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class),
                    messageHeaders.get(KafkaHeaders.OFFSET, Long.class),
                    messageHeaders.get(IntegrationMessageHeaderAccessor.DELIVERY_ATTEMPT, AtomicInteger.class));

            simpMessagingTemplate.convertAndSend("/topic/prices", priceDto);
        };
    }

}
