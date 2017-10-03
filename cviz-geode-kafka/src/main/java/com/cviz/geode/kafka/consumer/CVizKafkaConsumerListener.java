package com.cviz.geode.kafka.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import com.cviz.geode.common.api.AlertService;
import com.cviz.geode.rule.CVizSyslogEventXMLRule;

public class CVizKafkaConsumerListener {

	@Autowired
	private List<CVizSyslogEventXMLRule> cvizEventRules;

	@Autowired
	private AlertService alertService;

	@KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listenPartition0(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
		CVizLogProcessProcessor processor = new CVizLogProcessProcessor(records, cvizEventRules, alertService);
		processor.process();
		ack.acknowledge();
	}

}