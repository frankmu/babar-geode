package com.cviz.geode.kafka.consumer;

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
import com.cviz.geode.kafka.producer.CVizKafkaProducerSender;
import com.cviz.geode.kafka.util.CvizKafkaUtils;
import com.cviz.geode.rule.CVizEventRuleCondition;
import com.cviz.geode.rule.CVizEventRuleField;
import com.cviz.geode.rule.syslog.CVizSyslogEventRule;
import com.cviz.geode.rule.trap.CVizTrapEventRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CVizTrapProcessor {
	private List<ConsumerRecord<String, String>> records;
	private List<CVizTrapEventRule> cvizTrapEventRules;
	private AlertService alertService;
	private CVizKafkaProducerSender cVizKafkaProducerSender;
	private List<Alert> newAlerts;
	private Boolean enableCorrProcess;
	private final Log logger = LogFactory.getLog(CVizTrapProcessor.class);

	public CVizTrapProcessor(List<ConsumerRecord<String, String>> records, List<CVizTrapEventRule> cvizEventRules, AlertService alertService, CVizKafkaProducerSender cVizKafkaProducerSender) {
		this(records, cvizEventRules, alertService, cVizKafkaProducerSender, false);
	}

	public CVizTrapProcessor(List<ConsumerRecord<String, String>> records,
			List<CVizTrapEventRule> cvizTrapEventRules,
			AlertService alertService,
			CVizKafkaProducerSender cVizKafkaProducerSender,
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
		for (CVizTrapEventRule rule : cvizTrapEventRules) {
			Pattern trapReceivePattern = Pattern.compile(rule.getTrapReceiveTimePattern());
			Matcher trapReceiveMatcher = trapReceivePattern.matcher(message);
			if (!trapReceiveMatcher.find()) {
			    continue;
			}
			String receiveTime = trapReceiveMatcher.group(1);
			String formattedMessage = message.replace("receiveTime","").trim();
			String[] messageTokens = formattedMessage.split(rule.getTrapSeparator());
			if(isMessageMatchRule(messageTokens, rule.getTrapConditions())) {
				continue;
			}
			
			Map<String, String> variableMap = new HashMap<String, String>();
			for (int i = 0; i < rule.getRuleVariables().size(); i++) {
				int variableIndex = Integer.parseInt(rule.getRuleVariables().get(i).getIndex());
				variableMap.put("$" + rule.getRuleVariables().get(i).getValue(), messageTokens[variableIndex]);
			}
			variableMap.put("$receiveTime", receiveTime);
			Alert alert = new Alert();
			alert.setAlertUID(UUID.randomUUID().toString());
			alert.setSeverity(rule.getAlertSeverity()); 
			alert.setSourceMsg(message);
			alert.setMatchPrePolicy(rule.getRuleName());
			PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(alert);
			for (CVizEventRuleField field : rule.getRuleFields()) {
				String value = field.getValue();
				for (Map.Entry<String, String> entry : variableMap.entrySet()) {
					if (value.contains(entry.getKey())) {
						value = value.replace(entry.getKey(), entry.getValue());
					}
				}
				if("receiveTime".equals(field.getKey())){
					alert.setReceiveTime(CvizKafkaUtils.getFormattedTimestamp(value, rule.getReceiveTimeFormat()));
					continue;
				}
				myAccessor.setPropertyValue(field.getKey(), value);
			}
			newAlerts.add(alert);
			break;
		}
	}

	private boolean isMessageMatchRule(String[] tokens, List<CVizEventRuleCondition> conditions) {
		for(CVizEventRuleCondition condition: conditions) {
			int index = Integer.parseInt(condition.getIndex());
			if(index >= tokens.length) {
				return false;
			}
			if(!tokens[index].matches(condition.getValue())) {
				return false;
			}
		}
		return true;
	}
}