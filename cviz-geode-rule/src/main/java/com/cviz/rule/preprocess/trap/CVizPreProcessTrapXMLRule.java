package com.cviz.rule.preprocess.trap;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.cviz.rule.preprocess.CVizPreProcessRuleCondition;
import com.cviz.rule.preprocess.CVizPreProcessRuleField;
import com.cviz.rule.preprocess.CVizPreProcessRuleVariable;

@XmlRootElement(name="rule", namespace="http://www.example.org/cviz-rule")
public class CVizPreProcessTrapXMLRule extends CVizPreProcessTrapRule {

	@Override
	@XmlElement(name="name", namespace="http://www.example.org/cviz-rule")
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Override
	@XmlElement(name="priority", namespace="http://www.example.org/cviz-rule")
	public void setRuleSeq(int ruleSeq) {
		this.ruleSeq = ruleSeq;
	}

	@Override
	@XmlElement(name="action", namespace="http://www.example.org/cviz-rule")
	public void setProcMode(String procMode) {
		this.procMode = procMode;
	}

	@Override
	@XmlElement(name="severity", namespace="http://www.example.org/cviz-rule")
	public void setAlertSeverity(int alertSeverity) {
		this.alertSeverity = alertSeverity;
	}

	@Override
	@XmlElement(name="rule-type", namespace="http://www.example.org/cviz-rule")
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	@Override
	@XmlElement(name="receivetime-format", namespace="http://www.example.org/cviz-rule")
	public void setReceiveTimeFormat(String receiveTimeFormat) {
		this.receiveTimeFormat = receiveTimeFormat;
	}

	@Override
	@XmlElement(name="trap-receivetime-pattern", namespace="http://www.example.org/cviz-rule")
	public void setTrapReceiveTimePattern(String trapReceiveTimePattern) {
		this.trapReceiveTimePattern = trapReceiveTimePattern;
	}

	public List<CVizPreProcessRuleVariable> getRuleVariables() {
		return ruleVariables;
	}

	@Override
	@XmlElementWrapper(name = "variables", namespace="http://www.example.org/cviz-rule")
	@XmlElement(name="variable", namespace="http://www.example.org/cviz-rule")
	public void setRuleVariables(List<CVizPreProcessRuleVariable> ruleVariables) {
		this.ruleVariables = ruleVariables;
	}

	public List<CVizPreProcessRuleField> getRuleFields() {
		return ruleFields;
	}

	@Override
	@XmlElementWrapper(name = "fields", namespace="http://www.example.org/cviz-rule")
	@XmlElement(name="field", namespace="http://www.example.org/cviz-rule")
	public void setRuleFields(List<CVizPreProcessRuleField> ruleFields) {
		this.ruleFields = ruleFields;
	}

	public List<CVizPreProcessRuleCondition> getTrapConditions() {
		return trapConditions;
	}

	@Override
	@XmlElementWrapper(name = "conditions", namespace="http://www.example.org/cviz-rule")
	@XmlElement(name="condition", namespace="http://www.example.org/cviz-rule")
	public void setTrapConditions(List<CVizPreProcessRuleCondition> trapConditions) {
		this.trapConditions = trapConditions;
	}

	@Override
	@XmlElement(name="trap-seperator", namespace="http://www.example.org/cviz-rule")
	public void setTrapSeparator(String trapSeparator) {
		this.trapSeparator = trapSeparator;
	}
}