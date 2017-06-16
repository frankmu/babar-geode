package com.babar.geode.kafka.consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import com.babar.geode.event.BabarEventRule;
import com.babar.geode.event.BabarEventRuleField;
import com.babar.geode.rest.model.Alert;
import com.babar.geode.rest.service.BabarAlertService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class BabarLogProcessTask implements Runnable {
	private String message;
	private List<BabarEventRule> babarEventRules;
	private BabarAlertService babarAlertService;
	private final Log logger = LogFactory.getLog(BabarLogProcessTask.class);

	public BabarLogProcessTask(ConsumerRecord<String, String> record, List<BabarEventRule> babarEventRules, BabarAlertService babarAlertService) {        
        this.message = record.value();
        this.babarEventRules = babarEventRules;
        this.babarAlertService = babarAlertService;
    }

	public void run() {
		ObjectNode node = getJsonObjectNode();
		if (node.has("message")) {
//		    System.out.println("message: " + node.get("message"));
		    processMessage(node.get("message").toString());
		} 
	}

	private ObjectNode getJsonObjectNode(){
		ObjectNode node = null;
		try {
			node = new ObjectMapper().readValue(message, ObjectNode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	private void processMessage(String message){
		//message = "Jun  9 09:55:05 192.168.0.131 263030: *Jun  9 10:16:22.732: xxx.xxx.xxx.xx %SNMP-3-AUTHFAIL: Authentication failure for SNMP req from host 192.168.1.18";
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
	}
}