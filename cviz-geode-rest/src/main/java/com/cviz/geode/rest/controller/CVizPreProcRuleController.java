package com.cviz.geode.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cviz.geode.common.api.PreProcRuleService;
import com.cviz.geode.common.domain.PreProcRule;

@RestController
public class CVizPreProcRuleController {

	@Autowired
	private PreProcRuleService preProcRuleService;

	@CrossOrigin
	@RequestMapping(value = "/preprocrules", method = RequestMethod.GET)
	public List<PreProcRule> getAll(@RequestParam(value = "limit", defaultValue = "50") String limit) {
		return preProcRuleService.getAll(Integer.parseInt(limit));
	}

	@CrossOrigin
	@RequestMapping(value = "/preprocrules/{id}", method = RequestMethod.GET)
	public PreProcRule get(@PathVariable("id") String id) {
		return preProcRuleService.get(id);
	}

	@CrossOrigin
	@RequestMapping(value = "/preprocrules/{id}", method = RequestMethod.PUT)
	public PreProcRule update(@PathVariable("id") String id, @RequestBody PreProcRule preProcRule) {
		return preProcRuleService.save(id, preProcRule);
	}

	@CrossOrigin
	@RequestMapping(value = "/preprocrules/{id}", method = RequestMethod.DELETE)
	public PreProcRule remove(@PathVariable("id") String id) {
		return preProcRuleService.remove(id);
	}

	@CrossOrigin
	@RequestMapping(value = "/preprocrules", method = RequestMethod.POST)
	public PreProcRule save(@RequestBody PreProcRule preProcRule) {
		return preProcRuleService.create(preProcRule);
	}

	@CrossOrigin
	@RequestMapping(value = "/preprocrules/createdemodata", method = RequestMethod.GET)
	public void createDemoData() {
		preProcRuleService.createDemoData();
	}
}