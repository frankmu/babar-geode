package com.cviz.preprocess.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import com.cviz.geode.common.api.AlertService;
import com.cviz.geode.common.domain.Alert;
import com.cviz.preprocess.producer.CVizPreProcessProducerSender;
import com.cviz.preprocess.rule.CVizPreProcessRuleCondition;
import com.cviz.preprocess.rule.CVizPreProcessRuleField;
import com.cviz.preprocess.rule.trap.CVizPreProcessTrapRule;
import com.cviz.preprocess.util.CvizPreProcessUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CVizPreProcessTrapProcessor {
	private List<ConsumerRecord<String, String>> records;
	private List<CVizPreProcessTrapRule> cvizTrapEventRules;
	private AlertService alertService;
	private CVizPreProcessProducerSender cVizKafkaProducerSender;
	private List<Alert> newAlerts;
	private Boolean enableCorrProcess;
	private final Log logger = LogFactory.getLog(CVizPreProcessTrapProcessor.class);

	public CVizPreProcessTrapProcessor(List<ConsumerRecord<String, String>> records, List<CVizPreProcessTrapRule> cvizEventRules, AlertService alertService, CVizPreProcessProducerSender cVizKafkaProducerSender) {
		this(records, cvizEventRules, alertService, cVizKafkaProducerSender, false);
	}

	public CVizPreProcessTrapProcessor(List<ConsumerRecord<String, String>> records,
			List<CVizPreProcessTrapRule> cvizTrapEventRules,
			AlertService alertService,
			CVizPreProcessProducerSender cVizKafkaProducerSender,
			Boolean enableCorrProcess) {
		this.records = records;
		this.cvizTrapEventRules = cvizTrapEventRules;
		this.alertService = alertService;
		this.cVizKafkaProducerSender = cVizKafkaProducerSender;
		this.newAlerts = new ArrayList<Alert>();
		this.enableCorrProcess = enableCorrProcess;
	}

	public void process() {
		for(ConsumerRecord<String, String> record : this.records){
			ObjectNode node = getJsonObjectNode(record.value());
			if (node.has("message")) {
				processMessage(node.get("message").textValue());
			}
		}
		if(!enableCorrProcess) {
			if(alertService.saveAll(this.newAlerts)) {
				for(Alert alert : this.newAlerts) {
					logger.info("Insert alert to database - message: " + alert.getSourceMsg());
				}
			}
		}else {
			cVizKafkaProducerSender.sendForCorrProcess(newAlerts);
		}
	}

	private ObjectNode getJsonObjectNode(String message) {
		ObjectNode node = null;
		try {
			node = new ObjectMapper().readValue(message, ObjectNode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	private void processMessage(String message){
		for (CVizPreProcessTrapRule rule : cvizTrapEventRules) {
			Pattern trapReceivePattern = Pattern.compile(rule.getTrapReceiveTimePattern());
			Matcher trapReceiveMatcher = trapReceivePattern.matcher(message);
			if (!trapReceiveMatcher.find() || trapReceiveMatcher.groupCount() < 1) {
				logger.info("Cannot match rule " + rule.getRuleName() + " with message ["+ message + "] on receiveTime Pattern.");
			    continue;
			}
			String receiveTime = trapReceiveMatcher.group(1);
			String formattedMessage = message.replace(trapReceiveMatcher.group(0),"").trim();
			String[] messageTokens = formattedMessage.split(rule.getTrapSeparator());
			// if not match this rule, continue to next rule and skip the following processing
			if(!isMessageMatchRule(messageTokens, rule.getTrapConditions())) {
				continue;
			}
			
			Map<String, String> variableMap = new HashMap<String, String>();
			for (int i = 0; i < rule.getRuleVariables().size(); i++) {
				int variableIndex = Integer.parseInt(rule.getRuleVariables().get(i).getIndex());
				variableMap.put("$" + rule.getRuleVariables().get(i).getValue(), messageTokens[variableIndex]);
			}
			variableMap.put("$receivetime", receiveTime);
			Alert alert = new Alert();
			alert.setAlertUID(UUID.randomUUID().toString());
			alert.setSeverity(rule.getAlertSeverity()); 
			alert.setSourceMsg(message);
			alert.setMatchPrePolicy(rule.getRuleName());
			PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(alert);
			for (CVizPreProcessRuleField field : rule.getRuleFields()) {
				String value = field.getValue();
				for (Map.Entry<String, String> entry : variableMap.entrySet()) {
					if (value.contains(entry.getKey())) {
						value = value.replace(entry.getKey(), entry.getValue());
					}
				}
				if("receiveTime".equals(field.getKey())){
					alert.setReceiveTime(CvizPreProcessUtils.getFormattedTimestamp(value, rule.getReceiveTimeFormat()));
					continue;
				}
				myAccessor.setPropertyValue(field.getKey(), value);
			}
			newAlerts.add(alert);
			break;
		}
	}

	private boolean isMessageMatchRule(String[] tokens, List<CVizPreProcessRuleCondition> conditions) {
		for(CVizPreProcessRuleCondition condition: conditions) {
			int index = Integer.parseInt(condition.getIndex());
			if(index >= tokens.length || !tokens[index].trim().matches(condition.getValue())) {
				return false;
			}
		}
		return true;
	}
}