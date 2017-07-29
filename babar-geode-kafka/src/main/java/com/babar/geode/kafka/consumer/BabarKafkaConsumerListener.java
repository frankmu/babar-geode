package com.babar.geode.kafka.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.babar.geode.rule.BabarEventRule;
import com.cviz.geode.common.api.AlertService;

public class BabarKafkaConsumerListener {

	@Autowired
	private ThreadPoolTaskExecutor babarWorkerExecutor;

	@Autowired
	private List<BabarEventRule> babarEventRules;

	@Autowired
	private AlertService alertService;

	@KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listenPartition0(ConsumerRecord<String, String> record) {
		babarWorkerExecutor.execute(new BabarLogProcessTask(record, babarEventRules, alertService));
	}
}