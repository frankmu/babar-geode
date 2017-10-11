package com.cviz.preprocess.rule.trap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.cviz.geode.common.domain.PreProcRule;
import com.cviz.preprocess.rule.CVizPreProcessRuleCondition;
import com.cviz.preprocess.rule.CVizPreProcessRuleField;
import com.cviz.preprocess.rule.CVizPreProcessRuleVariable;

public class CVizPreProcessTrapDBRule extends CVizPreProcessTrapRule {

	public CVizPreProcessTrapDBRule(PreProcRule preProcRule) {
		this.ruleID = preProcRule.getRuleID();
		this.active = preProcRule.getActive();
		this.ruleName = preProcRule.getRuleName();
		this.ruleType = preProcRule.getRuleType();
		this.ruleSeq = Integer.parseInt(preProcRule.getRuleSeq());
		this.procMode = preProcRule.getProcMode();
		this.alertSeverity = Integer.parseInt(preProcRule.getAlertSeverity());
		this.receiveTimeFormat = preProcRule.getReceiveTimeFormat();
		this.trapSeparator = preProcRule.getTrapSeparator();
		populateRuleVariables(preProcRule.getRuleVariables());
		populateRuleFields(preProcRule.getRuleVariables());
		populateTrapRuleConditions(preProcRule.getTrapConditions());
	}

	private void populateRuleVariables(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizPreProcessRuleVariable> variables = new ArrayList<CVizPreProcessRuleVariable>();
		for (String key : jObject.keySet()) {
			CVizPreProcessRuleVariable variable = new CVizPreProcessRuleVariable(key, jObject.getString(key));
			variables.add(variable);
	    }
		Collections.sort(variables);
		this.ruleVariables = variables;
	}

	private void populateRuleFields(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizPreProcessRuleField> fields = new ArrayList<CVizPreProcessRuleField>();
		for (String key : jObject.keySet()) {
			CVizPreProcessRuleField field = new CVizPreProcessRuleField(key, jObject.getString(key));
			fields.add(field);
	    }
		this.ruleFields = fields;
	}

	private void populateTrapRuleConditions(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizPreProcessRuleCondition> conditions = new ArrayList<CVizPreProcessRuleCondition>();
		for (String key : jObject.keySet()) {
			CVizPreProcessRuleCondition condition = new CVizPreProcessRuleCondition(key, jObject.getString(key));
			conditions.add(condition);
	    }
		this.trapConditions = conditions;
	}
}