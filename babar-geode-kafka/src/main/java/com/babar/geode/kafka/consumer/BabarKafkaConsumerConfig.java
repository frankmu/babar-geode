package com.babar.geode.kafka.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import com.babar.geode.event.BabarEventRule;

@Configuration
public class BabarKafkaConsumerConfig {

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${kafka.topic.partition.count}")
	private int topicPartionCount;

	@Value("${kafka.consumer.task.executor.core.pool.size}")
	private int kafkaConsumerTaskExecutorCorePoolSize;

	@Value("classpath*:config/*.xml")
	private Resource[] files;

	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(topicPartionCount);
		factory.getContainerProperties().setConsumerTaskExecutor(consumerTaskExecutor());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}
	
	@Bean
	public AsyncListenableTaskExecutor consumerTaskExecutor() {
		ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
		tpte.setCorePoolSize(kafkaConsumerTaskExecutorCorePoolSize);
		return tpte;
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "spring-integration" + UUID.randomUUID().toString());
		// automatically reset the offset to the earliest offset
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return properties;
	}

	@Bean
	public BabarKafkaConsumerListener handler() {
		return new BabarKafkaConsumerListener();
	}

	@Bean
	ThreadPoolTaskExecutor babarWorkerExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		return executor;
	}

	@Bean
	List<BabarEventRule> babarEventRules() throws IOException, JAXBException {
		Assert.notEmpty(files, "No rule files found!");
		List<BabarEventRule> list = new ArrayList<BabarEventRule>();
		for(Resource file : files){
			JAXBContext jaxbContext = JAXBContext.newInstance(BabarEventRule.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			BabarEventRule rule = (BabarEventRule) jaxbUnmarshaller.unmarshal(file.getFile());
			list.add(rule);
		}
		return list;
	}
}