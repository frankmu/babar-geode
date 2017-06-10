package com.babar.geode.kafka.consumer.channel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class BabarKafkaConsumerHandler {
	private final Log logger = LogFactory.getLog(BabarKafkaConsumerHandler.class);
 
    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenPartition0(ConsumerRecord<?, ?> record) {
        logger.info(record);
    }
}