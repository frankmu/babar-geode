package com.cviz.preprocess.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import com.cviz.geode.common.api.AlertService;
import com.cviz.preprocess.producer.CVizPreProcessProducerSender;
import com.cviz.preprocess.rule.trap.CVizPreProcessTrapRule;

public class CVizPreProcessConsumerTrapListener implements CVizPreProcessConsumerListener{

	@Value("${enableCorrProcess}")
	private Boolean enableCorrProcess;

	@Autowired
	private List<CVizPreProcessTrapRule> cvizEventRules;

	@Autowired
	private AlertService alertService;

	@Autowired
	private CVizPreProcessProducerSender cVizKafkaProducerSender;

	@Override
	@KafkaListener(topics = "${kafka.consumer.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listenPartition0(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
		CVizPreProcessProcessor processor = new CVizPreProcessProcessor(records, cvizEventRules, alertService, cVizKafkaProducerSender, enableCorrProcess);
		processor.process();
		ack.acknowledge();
	}
}