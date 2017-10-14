package com.cviz.corrprocess.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan({"com.cviz.geode.common", "com.cviz.corrprocess"})
public class CVizCorrProcessAppliation {
	public static void main(String[] args) {
		SpringApplication.run(CVizCorrProcessAppliation.class, args);
	}
}