package com.babar.geode.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.babar.geode.event.BabarEventRule;
import com.babar.geode.event.BabarEventRuleField;
import com.babar.geode.rest.model.Alert;
import com.babar.geode.rest.model.Resource;
import com.babar.geode.rest.repository.ResourceRepository;
import com.babar.geode.rest.service.BabarAlertService;

@Controller
public class BabarTestController {
	
	@Autowired
	private List<BabarEventRule> babarEventRules;
	
	@Autowired
	private BabarAlertService babarAlertService;
	
	@PostConstruct
	public String test() {
		System.out.println("Get region count: " + String.valueOf(resourceRepository.count()));
		Resource resource = new Resource();
		System.out.println(String.valueOf(resourceRepository.save(resource)));
		String message = "Jun  9 09:55:05 192.168.0.131 263030: *Jun  9 10:16:22.732: xxx.xxx.xxx.xx %SNMP-3-AUTHFAIL: Authentication failure for SNMP req from host 192.168.1.18";
		for(BabarEventRule rule : babarEventRules){
			if(message.matches(rule.getMatchPattern())){
				System.out.println("message: " + message);
				Pattern pattern = Pattern.compile(rule.getMatchPattern());
				Matcher matcher = pattern.matcher(message);
				if (matcher.matches()) {
					Map<String, String> variableMap = new HashMap<String, String>();
					for(int i = 0; i < rule.getVairalbes().size(); i++){
						variableMap.put("$" + rule.getVairalbes().get(i), matcher.group(i + 1));
					}
					Alert alert = new Alert();
					PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(alert);
					for(BabarEventRuleField field : rule.getFields()){
						String value = field.getValue();
						for (Map.Entry<String, String> entry : variableMap.entrySet()) {
							if(value.contains(entry.getKey())){
								value = value.replace(entry.getKey(), entry.getValue());
							}
						}
						myAccessor.setPropertyValue(field.getKey(), value);
					}
					babarAlertService.insertAlert(alert);
				}
			}
		}
		return null;
	}

	@Autowired
	private ResourceRepository resourceRepository;
}