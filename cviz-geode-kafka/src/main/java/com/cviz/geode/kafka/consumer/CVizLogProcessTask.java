package com.cviz.geode.kafka.consumer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import com.cviz.geode.common.api.AlertService;
import com.cviz.geode.common.domain.Alert;
import com.cviz.geode.rule.CVizEventRule;
import com.cviz.geode.rule.CVizEventRuleField;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CVizLogProcessTask implements Runnable {
	private List<ConsumerRecord<String, String>> records;
	private List<CVizEventRule> cvizEventRules;
	private AlertService alertService;
	private final Log logger = LogFactory.getLog(CVizLogProcessTask.class);
	private static AtomicLong COUNTER = new AtomicLong(0L);

	public CVizLogProcessTask(List<ConsumerRecord<String, String>> records, List<CVizEventRule> cvizEventRules, AlertService alertService) {
		this.records = records;
		this.cvizEventRules = cvizEventRules;
		this.alertService = alertService;
	}

	public void run() {
		for(ConsumerRecord<String, String> record : this.records){
			ObjectNode node = getJsonObjectNode(record.value());
			if (node.has("message") && node.has("@timestamp")) {
				processMessage(node.get("message").textValue(), node.get("@timestamp").textValue());
			}
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

	private void processMessage(String message, String timestamp){
		for (CVizEventRule rule : cvizEventRules) {
			if (message.matches(rule.getMatchPattern())) {
				Pattern pattern = Pattern.compile(rule.getMatchPattern());
				Matcher matcher = pattern.matcher(message);
				if (matcher.matches()) {
					Map<String, String> variableMap = new HashMap<String, String>();
					for (int i = 0; i < rule.getVairalbes().size(); i++) {
						variableMap.put("$" + rule.getVairalbes().get(i), matcher.group(i + 1));
					}
					Alert alert = new Alert();
					alert.setId(UUID.randomUUID().toString());
					alert.setSeverity(rule.getSeverity());
					alert.setSourceMsg(message);
					alert.setMatchPolicy(rule.getName());
					PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(alert);
					for (CVizEventRuleField field : rule.getFields()) {
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
					alertService.save(alert.getId(), alert);
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
			alert.setAlertTime(date.getTime());
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