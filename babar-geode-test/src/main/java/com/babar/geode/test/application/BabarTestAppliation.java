package com.babar.geode.test.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.boot.SpringApplication;

@ComponentScan(value="com.babar.geode.test")
@ImportResource("client-cache.xml")
public class BabarTestAppliation {
	public static void main(String[] args) {
		SpringApplication.run(BabarTestAppliation.class, args);
	}
}