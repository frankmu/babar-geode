package com.cviz.preprocess.consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.AbstractMessageListenerContainer.AckMode;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.cviz.rule.preprocess.CVizPreProcessType;
import com.cviz.rule.preprocess.syslog.CVizPreProcessSyslogRule;
import com.cviz.rule.preprocess.syslog.CVizPreProcessSyslogRuleCondition;
import com.cviz.rule.preprocess.syslog.CvizPreProcessSyslogRuleFactory;
import com.cviz.rule.preprocess.trap.CVizPreProcessTrapRule;
import com.cviz.rule.preprocess.trap.CVizPreProcessTrapRuleCondition;
import com.cviz.rule.preprocess.trap.CvizPreProcessTrapRuleFactory;

@Configuration
public class CVizPreProcessConsumerConfig {

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${kafka.consumer.number}")
	private int kafkaConsumerNumber;

	@Value("${kafka.consumer.group.name}")
	private String kafkaConsumerGroupName;

	@Value("${kafka.consumer.batch.size}")
	private String kafkaConsumerbatchSize;

	@Value("${preprocess.rule.type}")
	private String ruleType;

	@Autowired
	private CvizPreProcessSyslogRuleFactory cvizPreProcessSyslogRuleFactory;

	@Autowired
	private CvizPreProcessTrapRuleFactory cvizPreProcessTrapRuleFactory;

	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(kafkaConsumerNumber);
		factory.setBatchListener(true);
		factory.getContainerProperties().setConsumerTaskExecutor(consumerTaskExecutor());
		factory.getContainerProperties().setAckMode(AckMode.MANUAL);
		return factory;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}
	
	@Bean
	public AsyncListenableTaskExecutor consumerTaskExecutor() {
		ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
		tpte.setCorePoolSize(kafkaConsumerNumber);
		return tpte;
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerGroupName);
		// automatically reset the offset to the earliest offset
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConsumerbatchSize);
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		return properties;
	}

	@Bean
	public CVizPreProcessConsumerListener handler() {
		if(ruleType.equalsIgnoreCase(CVizPreProcessType.SYSLOG.toString())) {
			return new CVizPreProcessConsumerSyslogListener();
		}else if(ruleType.equalsIgnoreCase(CVizPreProcessType.TRAP.toString())) {
			return new CVizPreProcessConsumerTrapListener();
		}else {
			return null;
		}
	}

	@Bean(name = "cvizEventRules")
	@Conditional(CVizPreProcessSyslogRuleCondition.class)
	public List<CVizPreProcessSyslogRule> getCVizSyslogEventRule() throws IOException, JAXBException{
		return cvizPreProcessSyslogRuleFactory.create();
	}

	@Bean(name = "cvizEventRules")
	@Conditional(CVizPreProcessTrapRuleCondition.class)
	public List<CVizPreProcessTrapRule> getCVizTrapEventRule() throws IOException, JAXBException{
		return cvizPreProcessTrapRuleFactory.create();
	}
}