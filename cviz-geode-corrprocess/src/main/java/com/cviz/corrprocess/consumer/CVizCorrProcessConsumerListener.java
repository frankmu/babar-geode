package com.cviz.corrprocess.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import com.cviz.geode.common.api.AlertService;

public class CVizCorrProcessConsumerListener {

	@Autowired
	protected AlertService alertService;

	@KafkaListener(topics = "${kafka.consumer.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listenPartition0(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
		
	}
}