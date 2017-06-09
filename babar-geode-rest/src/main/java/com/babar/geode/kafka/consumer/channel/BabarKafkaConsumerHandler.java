package com.babar.geode.kafka.consumer.channel;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class BabarKafkaConsumerHandler implements MessageHandler {

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		System.out.printf("received message='{%s}'\n", message);
	}
}