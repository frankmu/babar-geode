package com.cviz.geode.rest.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan({"com.cviz.geode"})
public class CVizRestAppliation {
	public static void main(String[] args) {
		SpringApplication.run(CVizRestAppliation.class, args);
	}
}