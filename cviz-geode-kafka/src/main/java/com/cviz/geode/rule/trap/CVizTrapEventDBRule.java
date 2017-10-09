package com.cviz.geode.rule.trap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.cviz.geode.common.domain.PreProcRule;
import com.cviz.geode.rule.CVizEventRuleCondition;
import com.cviz.geode.rule.CVizEventRuleField;
import com.cviz.geode.rule.CVizEventRuleVariable;

public class CVizTrapEventDBRule extends CVizTrapEventRule {

	public CVizTrapEventDBRule(PreProcRule preProcRule) {
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
		List<CVizEventRuleVariable> variables = new ArrayList<CVizEventRuleVariable>();
		for (String key : jObject.keySet()) {
			CVizEventRuleVariable variable = new CVizEventRuleVariable(key, jObject.getString(key));
			variables.add(variable);
	    }
		Collections.sort(variables);
		this.ruleVariables = variables;
	}

	private void populateRuleFields(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizEventRuleField> fields = new ArrayList<CVizEventRuleField>();
		for (String key : jObject.keySet()) {
			CVizEventRuleField field = new CVizEventRuleField(key, jObject.getString(key));
			fields.add(field);
	    }
		this.ruleFields = fields;
	}

	private void populateTrapRuleConditions(String jsonString) {
		JSONObject jObject = new JSONObject(jsonString);
		List<CVizEventRuleCondition> conditions = new ArrayList<CVizEventRuleCondition>();
		for (String key : jObject.keySet()) {
			CVizEventRuleCondition condition = new CVizEventRuleCondition(key, jObject.getString(key));
			conditions.add(condition);
	    }
		this.trapConditions = conditions;
	}
}