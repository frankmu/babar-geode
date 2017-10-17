package com.cviz.corrprocess.consumer;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import com.cviz.corrprocess.pipeline.CvizCorrInputRecordReader;
import com.cviz.geode.common.api.AlertService;
import com.cviz.geode.common.pipeline.domain.CvizAlertRecord;

public class CVizCorrProcessConsumerListener {

	@Autowired
	protected AlertService alertService;

	@SuppressWarnings("unused")
	@KafkaListener(topics = "${kafka.consumer.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listenPartition0(List<ConsumerRecord<String, CvizAlertRecord>> records, Acknowledgment ack) {
		CvizCorrInputRecordReader reader = new CvizCorrInputRecordReader(records.stream().map(item -> item.value()).collect(Collectors.toList()));
	}
}