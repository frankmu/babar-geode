package com.babar.geode.test.controller;

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

import com.babar.geode.common.repository.AlertRepository;
import com.babar.geode.kafka.service.BabarGeodeAlertService;
import com.babar.geode.rule.BabarEventRule;
import com.babar.geode.rule.BabarEventRuleField;
import com.babar.geode.common.model.Alert;

@Controller
public class BabarTestController {

	@PostConstruct
	public String test() {
//		System.out.println("Get region count: " + String.valueOf(alertRepository.count()));
//		Alert alert = new Alert();
//		System.out.println(String.valueOf(alertRepository.save(alert)));
//		String message = "Jun  9 09:55:05 192.168.0.131 263030: *Jun  9 10:16:22.732: xxx.xxx.xxx.xx %SNMP-3-AUTHFAIL: Authentication failure for SNMP req from host 192.168.1.18";
//		for (BabarEventRule rule : babarEventRules) {
//			if (message.matches(rule.getMatchPattern())) {
//				System.out.println("message: " + message);
//				Pattern pattern = Pattern.compile(rule.getMatchPattern());
//				Matcher matcher = pattern.matcher(message);
//				if (matcher.matches()) {
//					Map<String, String> variableMap = new HashMap<String, String>();
//					for (int i = 0; i < rule.getVairalbes().size(); i++) {
//						variableMap.put("$" + rule.getVairalbes().get(i), matcher.group(i + 1));
//					}
//					Alert a = new Alert();
//					PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(a);
//					for (BabarEventRuleField field : rule.getFields()) {
//						String value = field.getValue();
//						for (Map.Entry<String, String> entry : variableMap.entrySet()) {
//							if (value.contains(entry.getKey())) {
//								value = value.replace(entry.getKey(), entry.getValue());
//							}
//						}
//						myAccessor.setPropertyValue(field.getKey(), value);
//					}
//					babarGeodeAlertService.insertAlert(a);
//				}
//			}
//		}
		return null;
	}

	@Autowired
	private AlertRepository alertRepository;

	@Autowired
	private List<BabarEventRule> babarEventRules;

	@Autowired
	private BabarGeodeAlertService babarGeodeAlertService;
}