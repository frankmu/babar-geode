package com.cviz.geode.kafka.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.cviz.geode.common.api.AlertService;
import com.cviz.geode.rule.CVizEventRule;

public class CVizKafkaConsumerListener {

	@Autowired
	private ThreadPoolTaskExecutor cvizWorkerExecutor;

	@Autowired
	private List<CVizEventRule> cvizEventRules;

	@Autowired
	private AlertService alertService;

	@KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listenPartition0(ConsumerRecord<String, String> record) {
		cvizWorkerExecutor.execute(new CVizLogProcessTask(record, cvizEventRules, alertService));
	}
}