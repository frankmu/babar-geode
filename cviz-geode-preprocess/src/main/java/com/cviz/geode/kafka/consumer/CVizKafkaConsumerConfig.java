package com.cviz.geode.kafka.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.AbstractMessageListenerContainer.AckMode;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import com.cviz.geode.rule.CVizEventType;
import com.cviz.geode.rule.syslog.CVizSyslogEventRule;
import com.cviz.geode.rule.syslog.CVizSyslogEventRuleCondition;
import com.cviz.geode.rule.syslog.CVizSyslogEventXMLRule;
import com.cviz.geode.rule.trap.CVizTrapEventRule;
import com.cviz.geode.rule.trap.CVizTrapEventRuleCondition;
import com.cviz.geode.rule.trap.CVizTrapEventXMLRule;

@Configuration
public class CVizKafkaConsumerConfig {

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${kafka.consumer.number}")
	private int kafkaConsumerNumber;

	@Value("${kafka.consumer.group.name}")
	private String kafkaConsumerGroupName;

	@Value("${kafka.consumer.batch.size}")
	private String kafkaConsumerbatchSize;

	@Value("${rule.files}")
	private Resource[] files;

	@Value("${rule.type}")
	private String ruleType;

	@Value("${rule.source}")
	private String ruleSource;

	private final Log logger = LogFactory.getLog(CVizKafkaConsumerConfig.class);

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
	public CVizKafkaConsumerListener handler() {
		if(ruleType.equalsIgnoreCase(CVizEventType.SYSLOG.toString())) {
			return new CVizKafkaConsumerSyslogListener();
		}else if(ruleType.equalsIgnoreCase(CVizEventType.TRAP.toString())) {
			return new CVizKafkaConsumerTrapListener();
		}else {
			return null;
		}
	}

	@Bean(name = "cvizEventRules")
	@Conditional(CVizSyslogEventRuleCondition.class)
	public List<CVizSyslogEventRule> getCVizSyslogEventRule() throws IOException, JAXBException{
		List<CVizSyslogEventRule> list = new ArrayList<CVizSyslogEventRule>();
		if(ruleType.equalsIgnoreCase("file")) {
			Assert.notEmpty(files, "No rule files found! Please enter a valid rule file path");
			for(Resource file : files){
				JAXBContext jaxbContext = JAXBContext.newInstance(CVizSyslogEventXMLRule.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				CVizSyslogEventXMLRule rule = (CVizSyslogEventXMLRule) jaxbUnmarshaller.unmarshal(file.getInputStream());
				logger.info("Load rule file " + rule.getRuleName());
				list.add(rule);
			}
		}else if(ruleType.equalsIgnoreCase("database")) {
		}else {
		}
		return list;
	}

	@Bean(name = "cvizEventRules")
	@Conditional(CVizTrapEventRuleCondition.class)
	public List<CVizTrapEventRule> getCVizTrapEventRule() throws IOException, JAXBException{
		List<CVizTrapEventRule> list = new ArrayList<CVizTrapEventRule>();
		if(ruleType.equalsIgnoreCase("file")) {
			Assert.notEmpty(files, "No rule files found! Please enter a valid rule file path");
			for(Resource file : files){
				JAXBContext jaxbContext = JAXBContext.newInstance(CVizSyslogEventXMLRule.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				CVizTrapEventXMLRule rule = (CVizTrapEventXMLRule) jaxbUnmarshaller.unmarshal(file.getInputStream());
				logger.info("Load rule file " + rule.getRuleName());
				list.add(rule);
			}
		}else if(ruleType.equalsIgnoreCase("database")) {
		}else {
		}
		return list;
	}
}