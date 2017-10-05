package com.cviz.geode.kafka.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

public interface CVizKafkaConsumerListener {

	void listenPartition0(List<ConsumerRecord<String, String>> records, Acknowledgment ack);
}