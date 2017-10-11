package com.cviz.preprocess.producer;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.cviz.geode.common.domain.Alert;

public class CVizPreProcessProducerSender {

	private final Log logger = LogFactory.getLog(CVizPreProcessProducerSender.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.producer.topic.name}")
	private String producerTopic;

	public void sendForCorrProcess(Alert alert) {
		send(producerTopic, alert.toString());
	}

	public void sendForCorrProcess(List<Alert> alerts) {
		for(Alert alert : alerts) {
			send(producerTopic, alert.toString());
		}
	}

	private void send(String topic, String payload) {
		logger.info("sending payload='" + "' to topic='{" + topic + "}'");
		kafkaTemplate.send(topic, payload);
	}
}