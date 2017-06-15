package com.babar.geode.kafka.worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import com.babar.geode.event.BabarEventRule;

@Configuration
public class BabarLogProcessWorkerConfig {

	@Value("classpath*:config/*.xml")
	private Resource[] files;

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
