package com.cviz.geode.kafka.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan({"com.cviz.geode"})
public class CVizGeodeKafkaAppliation {
	public static void main(String[] args) {
		SpringApplication.run(CVizGeodeKafkaAppliation.class, args);
	}
}