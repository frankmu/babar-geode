package com.babar.geode.rest.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.repository.Wrapper;
import org.springframework.stereotype.Controller;

import com.babar.geode.rest.model.Resource;
import com.babar.geode.rest.repository.ResourceRepository;

@Controller
public class BabarTestController {
	
	@PostConstruct
	public String test() {
		System.out.println("Get resion count: " + String.valueOf(resourceRepository.count()));
		Resource alert = new Resource();
		Wrapper<Resource, String> w = new Wrapper<Resource, String>(alert, "");
		return String.valueOf(resourceRepository.save(w));
	}

	@Autowired
	private ResourceRepository resourceRepository;
}