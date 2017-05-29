package com.babar.geode.rest.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(value="com.babar.geode.rest")
@ImportResource("client-cache.xml")
public class BabarRestAppliation {
	public static void main(String[] args) {
		SpringApplication.run(BabarRestAppliation.class, args);
	}
}