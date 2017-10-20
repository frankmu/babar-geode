package com.cviz.preprocess.consumer;

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
import org.springframework.beans.factory.annotation.Autowired;
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

import com.cviz.geode.common.api.PreProcRuleService;
import com.cviz.geode.common.domain.PreProcRule;
import com.cviz.preprocess.rule.CVizPreProcessType;
import com.cviz.preprocess.rule.syslog.CVizPreProcessSyslogDBRule;
import com.cviz.preprocess.rule.syslog.CVizPreProcessSyslogRule;
import com.cviz.preprocess.rule.syslog.CVizPreProcessSyslogRuleCondition;
import com.cviz.preprocess.rule.syslog.CVizPreProcessSyslogXMLRule;
import com.cviz.preprocess.rule.trap.CVizPreProcessTrapDBRule;
import com.cviz.preprocess.rule.trap.CVizPreProcessTrapRule;
import com.cviz.preprocess.rule.trap.CVizPreProcessTrapRuleCondition;
import com.cviz.preprocess.rule.trap.CVizPreProcessTrapXMLRule;
import com.cviz.preprocess.util.CvizPreProcessConstant;

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

	@Value("${preprocess.rule.files}")
	private Resource[] files;

	@Value("${preprocess.rule.type}")
	private String ruleType;

	@Value("${preprocess.rule.source}")
	private String ruleSource;

	@Autowired
	private PreProcRuleService preProcRuleService;

	private final Log logger = LogFactory.getLog(CVizPreProcessConsumerConfig.class);

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
		List<CVizPreProcessSyslogRule> list = new ArrayList<CVizPreProcessSyslogRule>();
		if(ruleSource.equalsIgnoreCase(CvizPreProcessConstant.CVIZ_PREPROCESS_FILE_RULE_SOURCE)) {
			for(Resource file : files){
				JAXBContext jaxbContext = JAXBContext.newInstance(CVizPreProcessSyslogXMLRule.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				CVizPreProcessSyslogRule rule = (CVizPreProcessSyslogRule) jaxbUnmarshaller.unmarshal(file.getInputStream());
				logger.info("Load rule file " + rule.getRuleName());
				list.add(rule);
			}
		}else if(ruleSource.equalsIgnoreCase(CvizPreProcessConstant.CVIZ_PREPROCESS_DB_RULE_SOURCE)) {
			for(PreProcRule preProcRule : preProcRuleService.getAllByRuleType(CVizPreProcessType.SYSLOG.toString(), 100)) {
				list.add(new CVizPreProcessSyslogDBRule(preProcRule));
			}
		}
		if(list.isEmpty()) {
			logger.error("No syslog pre process rule found. Please add rules and try again.");
		}
		return list;
	}

	@Bean(name = "cvizEventRules")
	@Conditional(CVizPreProcessTrapRuleCondition.class)
	public List<CVizPreProcessTrapRule> getCVizTrapEventRule() throws IOException, JAXBException{
		List<CVizPreProcessTrapRule> list = new ArrayList<CVizPreProcessTrapRule>();
		if(ruleSource.equalsIgnoreCase(CvizPreProcessConstant.CVIZ_PREPROCESS_FILE_RULE_SOURCE)) {
			Assert.notEmpty(files, "No rule files found! Please enter a valid rule file path");
			for(Resource file : files){
				JAXBContext jaxbContext = JAXBContext.newInstance(CVizPreProcessTrapXMLRule.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				CVizPreProcessTrapXMLRule rule = (CVizPreProcessTrapXMLRule) jaxbUnmarshaller.unmarshal(file.getInputStream());
				logger.info("Load rule file " + rule.getRuleName());
				list.add(rule);
			}
		}else if(ruleSource.equalsIgnoreCase(CvizPreProcessConstant.CVIZ_PREPROCESS_DB_RULE_SOURCE)) {
			for(PreProcRule preProcRule : preProcRuleService.getAllByRuleType(CVizPreProcessType.TRAP.toString(), 100)) {
				list.add(new CVizPreProcessTrapDBRule(preProcRule));
			}
		}
		if(list.isEmpty()) {
			logger.error("No trap pre process rule found. Please add rules and try again.");
		}
		return list;
	}
}