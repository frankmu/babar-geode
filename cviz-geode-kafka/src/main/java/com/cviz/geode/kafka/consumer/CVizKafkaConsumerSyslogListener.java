package com.cviz.geode.kafka.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import com.cviz.geode.common.api.AlertService;
import com.cviz.geode.rule.CVizSyslogEventRule;

public class CVizKafkaConsumerSyslogListener implements CVizKafkaConsumerListener{

	@Autowired
	private List<CVizSyslogEventRule> cvizEventRules;

	@Autowired
	private AlertService alertService;

	@Override
	@KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listenPartition0(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
		CVizSyslogProcessor processor = new CVizSyslogProcessor(records, cvizEventRules, alertService);
		processor.process();
		ack.acknowledge();
	}

}