package com.cviz.geode.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cviz.geode.cache.domain.Alert;
import com.cviz.geode.common.api.AlertService;

@RestController
public class CVizAlertController {

	@Autowired
	private AlertService alertService;

	@CrossOrigin
	@RequestMapping(value = "/alerts", method = RequestMethod.GET)
	public Iterable<Alert> findAllOrderByTimestampLimit(@RequestParam(value = "limit", defaultValue = "50") String limit) {
		return alertService.findAllOrderByReceiveTimeDescLimit(Integer.parseInt(limit));
	}

	@CrossOrigin
	@RequestMapping(value = "/alerts/search/findByReceiveTime", method = RequestMethod.GET)
	public Iterable<Alert> findByReceiveTimeGreaterThanAndReceiveTimeLessThanOrderByReceiveTimeDescLimit(
			@RequestParam(value = "startTime", required = true) String startTime,
			@RequestParam(value = "endTime", required = true) String endTime,
			@RequestParam(value = "limit", defaultValue = "50") String limit) {
		return alertService.findByReceiveTimeGreaterThanAndReceiveTimeLessThanOrderByReceiveTimeDescLimit(
				Long.parseLong(startTime),
				Long.parseLong(endTime),
				Integer.parseInt(limit));
	}

	@CrossOrigin
	@RequestMapping(value = "/alerts/{id}", method = RequestMethod.DELETE)
	public Alert remove(@PathVariable("id") String id) {
		return alertService.remove(id);
	}
}