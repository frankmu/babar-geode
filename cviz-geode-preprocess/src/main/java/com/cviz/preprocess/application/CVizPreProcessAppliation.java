package com.cviz.preprocess.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan({"com.cviz.geode.common", "com.cviz.preprocess"})
public class CVizPreProcessAppliation {
	public static void main(String[] args) {
		SpringApplication.run(CVizPreProcessAppliation.class, args);
	}
}