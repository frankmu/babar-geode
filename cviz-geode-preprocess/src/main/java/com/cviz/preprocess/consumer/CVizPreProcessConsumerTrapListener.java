package com.cviz.preprocess.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import com.cviz.rule.preprocess.trap.CVizPreProcessTrapRule;

public class CVizPreProcessConsumerTrapListener extends CVizPreProcessConsumerListener{

	@Autowired
	private List<CVizPreProcessTrapRule> cvizEventRules;

	@Override
	@KafkaListener(topics = "${kafka.consumer.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listenPartition0(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
		CVizPreProcessTrapProcessor processor = new CVizPreProcessTrapProcessor(records, cvizEventRules, alertService, cVizKafkaProducerSender, enableCorrProcess);
		processor.process();
		ack.acknowledge();
	}
}