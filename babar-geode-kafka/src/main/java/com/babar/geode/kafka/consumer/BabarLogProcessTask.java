package com.babar.geode.kafka.consumer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.babar.geode.kafka.service.BabarGeodeAlertService;
import com.babar.geode.common.model.Alert;
import com.babar.geode.rule.BabarEventRule;
import com.babar.geode.rule.BabarEventRuleField;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class BabarLogProcessTask implements Runnable {
	private String message;
	private List<BabarEventRule> babarEventRules;
	private BabarGeodeAlertService babarGeodeAlertService;
	private final Log logger = LogFactory.getLog(BabarLogProcessTask.class);

	public BabarLogProcessTask(ConsumerRecord<String, String> record, List<BabarEventRule> babarEventRules,
			BabarGeodeAlertService babarAlertService) {
		this.message = record.value();
		this.babarEventRules = babarEventRules;
		this.babarGeodeAlertService = babarAlertService;
	}

	public void run() {
		ObjectNode node = getJsonObjectNode();
		if (node.has("message") && node.has("@timestamp")) {
			processMessage(node.get("message").textValue(), node.get("@timestamp").textValue());
		}
	}

	private ObjectNode getJsonObjectNode() {
		ObjectNode node = null;
		try {
			node = new ObjectMapper().readValue(message, ObjectNode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	private void processMessage(String message, String timestamp){
		for (BabarEventRule rule : babarEventRules) {
			if (message.matches(rule.getMatchPattern())) {
				Pattern pattern = Pattern.compile(rule.getMatchPattern());
				Matcher matcher = pattern.matcher(message);
				if (matcher.matches()) {
					Map<String, String> variableMap = new HashMap<String, String>();
					for (int i = 0; i < rule.getVairalbes().size(); i++) {
						variableMap.put("$" + rule.getVairalbes().get(i), matcher.group(i + 1));
					}
					Alert alert = new Alert();
					alert.setSeverity(rule.getSeverity());
					alert.setSourceMsg(message);
					alert.setMatchPolicy(rule.getName());
					PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(alert);
					for (BabarEventRuleField field : rule.getFields()) {
						String value = field.getValue();
						for (Map.Entry<String, String> entry : variableMap.entrySet()) {
							if (value.contains(entry.getKey())) {
								value = value.replace(entry.getKey(), entry.getValue());
							}
						}
						if("timestamp".equals(field.getKey())){
							setTimestampForAlert(alert, value, rule.getTimestampPattern());
							continue;
						}
						myAccessor.setPropertyValue(field.getKey(), value);
					}
					setReceivedTime(alert, timestamp);
					babarGeodeAlertService.insertAlert(alert);
					logger.info("Insert alert to database - message: " + message);
					continue;
				}
			}
		}
	}

	private void setTimestampForAlert(Alert alert, String timestamp, String pattern){
		try {
			SimpleDateFormat dt = new SimpleDateFormat(pattern);
			Date date = dt.parse(timestamp);
			if(!pattern.contains("yyyy")){
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
				date = c.getTime();
			}
			alert.setTimestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void setReceivedTime(Alert alert, String time){
		try {
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date date = dt.parse(time);
			alert.setReceiveTime(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}