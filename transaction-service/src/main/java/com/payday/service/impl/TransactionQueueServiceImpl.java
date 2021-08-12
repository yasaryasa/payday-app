package com.payday.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.payday.dto.TransactionDto;
import com.payday.service.TransactionQueueService;

@Service
@EnableBinding(Source.class)
public class TransactionQueueServiceImpl implements TransactionQueueService {

	@Autowired
    private Source source;

    @Override
    public void sendToQueue(TransactionDto transactionDto) {
        source.output().send(MessageBuilder.withPayload(transactionDto).build());
    }

}