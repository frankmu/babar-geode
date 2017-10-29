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

import com.cviz.geode.cache.domain.Alert;
import com.cviz.geode.common.api.AlertService;
import com.cviz.preprocess.producer.CVizPreProcessProducerSender;
import com.cviz.preprocess.util.CvizPreProcessUtils;
import com.cviz.rule.preprocess.CVizPreProcessRuleField;
import com.cviz.rule.preprocess.syslog.CVizPreProcessSyslogRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CVizPreProcessSyslogProcessor {
	private List<ConsumerRecord<String, String>> records;
	private List<CVizPreProcessSyslogRule> cvizEventRules;
	private AlertService alertService;
	private CVizPreProcessProducerSender cVizKafkaProducerSender;
	private List<Alert> newAlerts;
	private Boolean enableCorrProcess;
	private final Log logger = LogFactory.getLog(CVizPreProcessSyslogProcessor.class);

	public CVizPreProcessSyslogProcessor(List<ConsumerRecord<String, String>> records, List<CVizPreProcessSyslogRule> cvizEventRules, AlertService alertService, CVizPreProcessProducerSender cVizKafkaProducerSender) {
		this(records, cvizEventRules, alertService, cVizKafkaProducerSender, false);
	}

	public CVizPreProcessSyslogProcessor(List<ConsumerRecord<String, String>> records,
			List<CVizPreProcessSyslogRule> cvizEventRules,
			AlertService alertService,
			CVizPreProcessProducerSender cVizKafkaProducerSender,
			Boolean enableCorrProcess) {
		this.records = records;
		this.cvizEventRules = cvizEventRules;
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
			logger.error(e.getStackTrace());
		}
		return node;
	}

	private void processMessage(String message){
		for (CVizPreProcessSyslogRule rule : cvizEventRules) {
			if (message.matches(rule.getSyslogMatchPattern())) {
				Pattern pattern = Pattern.compile(rule.getSyslogMatchPattern());
				Matcher matcher = pattern.matcher(message);
				if (matcher.matches()) {
					Map<String, String> variableMap = new HashMap<String, String>();
					for (int i = 0; i < rule.getRuleVariables().size(); i++) {
						variableMap.put("$" + rule.getRuleVariables().get(i).getValue(), matcher.group(i + 1));
					}
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
						//TODO Need to transform the fault time to UTC, now just copy the data from receive time.
						if("faultTime".equals(field.getKey())){
							alert.setFaultTime(alert.getReceiveTime());
							continue;
						}
						myAccessor.setPropertyValue(field.getKey(), value);
					}
					String compressKey = alert.getCompressKey();
					if( compressKey == null || compressKey.isEmpty()) {
						compressKey = alert.getAlertObj() + alert.getAlertMsg();
					}
					alert.setCompressKey(String.valueOf(compressKey.hashCode()));
					newAlerts.add(alert);
					break;
				}
			}
		}
	}
}