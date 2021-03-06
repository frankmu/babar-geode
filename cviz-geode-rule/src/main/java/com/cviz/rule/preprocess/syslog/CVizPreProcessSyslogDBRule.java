package com.cviz.rule.preprocess.syslog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.cviz.geode.cache.domain.PreProcRule;
import com.cviz.rule.preprocess.CVizPreProcessRuleField;
import com.cviz.rule.preprocess.CVizPreProcessRuleVariable;

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
		populateRuleFields(preProcRule.getRuleFields());
		
	}

	private void populateRuleVariables(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizPreProcessRuleVariable> variables = new ArrayList<CVizPreProcessRuleVariable>();
		for (String key : jObject.keySet()) {
			CVizPreProcessRuleVariable variable = new CVizPreProcessRuleVariable();
			variable.setIndex(key);
			variable.setValue(jObject.getString(key));
			variables.add(variable);
	    }
		Collections.sort(variables);
		this.ruleVariables = variables;
	}

	private void populateRuleFields(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizPreProcessRuleField> variables = new ArrayList<CVizPreProcessRuleField>();
		for (String key : jObject.keySet()) {
			CVizPreProcessRuleField variable = new CVizPreProcessRuleField();
			variable.setKey(key);
			variable.setValue(jObject.getString(key));
			variables.add(variable);
	    }
		this.ruleFields = variables;
	}
}