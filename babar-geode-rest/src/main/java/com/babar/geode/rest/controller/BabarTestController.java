package com.babar.geode.rest.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.babar.geode.common.repository.AlertRepository;
import com.babar.geode.common.model.Alert;

@Controller
public class BabarTestController {

	@PostConstruct
	public String test() {
//		System.out.println("Get region count: " + String.valueOf(alertRepository.count()));
//		Alert alert = new Alert();
//		System.out.println(String.valueOf(alertRepository.save(alert)));
		return null;
	}

	@Autowired
	private AlertRepository alertRepository;
}