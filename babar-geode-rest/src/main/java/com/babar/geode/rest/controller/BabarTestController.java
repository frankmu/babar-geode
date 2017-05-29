package com.babar.geode.rest.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.babar.geode.rest.repository.ResourceRepository;

@Controller
public class BabarTestController {
	
	@PostConstruct
	public String test() {
		System.out.println("Get resion count: " + String.valueOf(resourceRepository.count()));
		return String.valueOf(resourceRepository.count());
	}

	@Autowired
	private ResourceRepository resourceRepository;
}