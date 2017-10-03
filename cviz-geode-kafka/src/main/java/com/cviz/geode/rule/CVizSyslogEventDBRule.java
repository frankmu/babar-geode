package com.cviz.geode.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.cviz.geode.common.domain.PreProcRule;

public class CVizSyslogEventDBRule extends CVizEventRule {

	private String syslogMatchPattern;	//regex: 匹配消息正文全匹配(syslog)
	private String syslogMatchNode;  	//regex: 匹配node名称(syslog)

	public CVizSyslogEventDBRule(PreProcRule preProcRule) {
		this.ruleID = preProcRule.getRuleID();
		this.active = preProcRule.getActive();
		this.ruleName = preProcRule.getRuleName();
		this.ruleType = preProcRule.getRuleType();
		this.ruleSeq = Integer.parseInt(preProcRule.getRuleSeq());
		this.procMode = preProcRule.getProcMode();
		this.alertSeverity = Integer.parseInt(preProcRule.getAlertSeverity());
		this.receiveTimePattern = preProcRule.getReceiveTimePattern();
		this.syslogMatchPattern = preProcRule.getSyslogMatchPattern();
		this.syslogMatchNode = preProcRule.getSyslogMatchNode();
		populateRuleVariables(preProcRule.getRuleVariables());
		populateRuleFields(preProcRule.getRuleVariables());
	}

	public String getSyslogMatchPattern() {
		return syslogMatchPattern;
	}

	public void setSyslogMatchPattern(String syslogMatchPattern) {
		this.syslogMatchPattern = syslogMatchPattern;
	}

	public String getSyslogMatchNode() {
		return syslogMatchNode;
	}

	public void setNodeName(String syslogMatchNode) {
		this.syslogMatchNode = syslogMatchNode;
	}

	private void populateRuleVariables(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizEventRuleVariable> variables = new ArrayList<CVizEventRuleVariable>();
		for (String key : jObject.keySet()) {
			CVizEventRuleVariable variable = new CVizEventRuleVariable(key, jObject.getString(key));
			variables.add(variable);
	    }
		Collections.sort(variables);
	}

	private void populateRuleFields(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizEventRuleField> variables = new ArrayList<CVizEventRuleField>();
		for (String key : jObject.keySet()) {
			CVizEventRuleField variable = new CVizEventRuleField(key, jObject.getString(key));
			variables.add(variable);
	    }
	}
}