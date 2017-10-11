package com.cviz.preprocess.rule.syslog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.cviz.geode.common.domain.PreProcRule;
import com.cviz.preprocess.rule.CVizPreProcessRuleField;
import com.cviz.preprocess.rule.CVizPreProcessRuleVariable;

public class CVizPreProcessSyslogDBRule extends CVizPreProcessSyslogRule {

	public CVizPreProcessSyslogDBRule(PreProcRule preProcRule) {
		this.ruleID = preProcRule.getRuleID();
		this.active = preProcRule.getActive();
		this.ruleName = preProcRule.getRuleName();
		this.ruleType = preProcRule.getRuleType();
		this.ruleSeq = Integer.parseInt(preProcRule.getRuleSeq());
		this.procMode = preProcRule.getProcMode();
		this.alertSeverity = Integer.parseInt(preProcRule.getAlertSeverity());
		this.receiveTimeFormat = preProcRule.getReceiveTimeFormat();
		this.syslogMatchPattern = preProcRule.getSyslogMatchPattern();
		this.syslogMatchNode = preProcRule.getSyslogMatchNode();
		populateRuleVariables(preProcRule.getRuleVariables());
		populateRuleFields(preProcRule.getRuleVariables());
		
	}

	private void populateRuleVariables(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizPreProcessRuleVariable> variables = new ArrayList<CVizPreProcessRuleVariable>();
		for (String key : jObject.keySet()) {
			CVizPreProcessRuleVariable variable = new CVizPreProcessRuleVariable(key, jObject.getString(key));
			variables.add(variable);
	    }
		Collections.sort(variables);
	}

	private void populateRuleFields(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizPreProcessRuleField> variables = new ArrayList<CVizPreProcessRuleField>();
		for (String key : jObject.keySet()) {
			CVizPreProcessRuleField variable = new CVizPreProcessRuleField(key, jObject.getString(key));
			variables.add(variable);
	    }
	}
}