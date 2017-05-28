package com.babar.geode.test.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import com.babar.geode.test.repository.ProductRepository;
import org.springframework.stereotype.Controller;

@Controller
public class BabarTestController {
	
	@PostConstruct
	public String test() {
		System.out.println("Get resion count: " + String.valueOf(productRepository.count()));
		return String.valueOf(productRepository.count());
	}

	@Autowired
	private ProductRepository productRepository;
}