package com.cviz.preprocess.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.Acknowledgment;

import com.cviz.geode.common.api.AlertService;
import com.cviz.preprocess.producer.CVizPreProcessProducerSender;

public abstract class CVizPreProcessConsumerListener {

	@Value("${corrprocess.enable}")
	protected Boolean enableCorrProcess;

	@Autowired
	protected AlertService alertService;

	@Autowired
	protected CVizPreProcessProducerSender cVizKafkaProducerSender;

	abstract void listenPartition0(List<ConsumerRecord<String, String>> records, Acknowledgment ack);
}