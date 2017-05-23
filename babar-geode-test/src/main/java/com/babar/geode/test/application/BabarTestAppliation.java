package com.babar.geode.test.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value="com.babar.geode.test")
@EnableAutoConfiguration
public class BabarTestAppliation {
	public static void main(String[] args) {
		SpringApplication.run(BabarTestAppliation.class, args);
	}
}