package com.babar.geode.kafka.worker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class BabarLogProcessWorkerConfig {

	@Bean
	ThreadPoolTaskExecutor babarWorkerExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		return executor;
	}
}
