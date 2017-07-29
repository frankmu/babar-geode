package com.babar.geode.kafka.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan({"com.babar.geode","com.cviz.geode"})
public class BabarGeodeKafkaAppliation {
	public static void main(String[] args) {
		SpringApplication.run(BabarGeodeKafkaAppliation.class, args);
	}
}