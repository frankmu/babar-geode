package com.babar.geode.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.babar.geode.kafka.worker.BabarLogProcessTask;

public class BabarKafkaConsumerListener {

	@Autowired
	private ThreadPoolTaskExecutor babarWorkerExecutor;
 
    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenPartition0(ConsumerRecord<?, ?> record) {
    	babarWorkerExecutor.execute(new BabarLogProcessTask(record));
    }
}