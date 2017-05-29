package com.babar.geode.rest.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.babar.geode.rest.repository.ProductRepository;

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