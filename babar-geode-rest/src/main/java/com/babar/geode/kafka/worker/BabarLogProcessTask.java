package com.babar.geode.kafka.worker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class BabarLogProcessTask implements Runnable {
	private String message;
	private final Log logger = LogFactory.getLog(BabarLogProcessTask.class);

	public BabarLogProcessTask(ConsumerRecord<?, ?> record) {        
        this.message = record.toString();      
    }

	public void run() {
		logger.info(message);
	}
}