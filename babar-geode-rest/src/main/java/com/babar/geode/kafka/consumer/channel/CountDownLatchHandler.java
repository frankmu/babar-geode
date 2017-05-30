package com.babar.geode.kafka.consumer.channel;

import java.util.concurrent.CountDownLatch;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class CountDownLatchHandler implements MessageHandler {

	  private CountDownLatch latch = new CountDownLatch(10);

	  public CountDownLatch getLatch() {
	    return latch;
	  }

	  @Override
	  public void handleMessage(Message<?> message) throws MessagingException {
	    System.out.printf("received message='{%s}'\n", message);
	    latch.countDown();
	  }
	}