package com.babar.geode.rest.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(value="com.babar.geode")
public class BabarRestAppliation {
	public static void main(String[] args) {
		SpringApplication.run(BabarRestAppliation.class, args);
	}
}