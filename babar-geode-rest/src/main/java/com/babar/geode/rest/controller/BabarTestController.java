package com.babar.geode.rest.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cviz.geode.common.api.AlertService;

@Controller
public class BabarTestController {

	@PostConstruct
	public String test() {
		//alertService.findAllOrderByTimestampLimit(10);
		return null;
	}

	@Autowired
	private AlertService alertService;
}