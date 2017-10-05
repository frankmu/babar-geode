package com.cviz.geode.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.cviz.geode.common.domain.PreProcRule;

public class CVizTrapEventDBRule extends CVizTrapEventRule {

	public CVizTrapEventDBRule(PreProcRule preProcRule) {
		this.ruleID = preProcRule.getRuleID();
		this.active = preProcRule.getActive();
		this.ruleName = preProcRule.getRuleName();
		this.ruleType = preProcRule.getRuleType();
		this.ruleSeq = Integer.parseInt(preProcRule.getRuleSeq());
		this.procMode = preProcRule.getProcMode();
		this.alertSeverity = Integer.parseInt(preProcRule.getAlertSeverity());
		this.receiveTimePattern = preProcRule.getReceiveTimePattern();
		this.trapSeparator = preProcRule.getTrapSeparator();
		this.trapConditions = preProcRule.getTrapConditions();
		populateRuleVariables(preProcRule.getRuleVariables());
		populateRuleFields(preProcRule.getRuleVariables());
		
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