package com.cviz.preprocess.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

public interface CVizPreProcessConsumerListener {

	void listenPartition0(List<ConsumerRecord<String, String>> records, Acknowledgment ack);
}