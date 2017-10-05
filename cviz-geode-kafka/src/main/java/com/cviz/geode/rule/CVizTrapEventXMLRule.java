package com.cviz.geode.rule;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rule", namespace="http://www.example.org/cviz-rule")
public class CVizTrapEventXMLRule extends CVizTrapEventRule {

	@Override
	@XmlElement(namespace="http://www.example.org/cviz-rule")
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Override
	@XmlElement(namespace="http://www.example.org/cviz-rule")
	public void setRuleSeq(int ruleSeq) {
		this.ruleSeq = ruleSeq;
	}

	@Override
	@XmlElement(namespace="http://www.example.org/cviz-rule")
	public void setProcMode(String procMode) {
		this.procMode = procMode;
	}

	@Override
	@XmlElement(namespace="http://www.example.org/cviz-rule")
	public void setAlertSeverity(int alertSeverity) {
		this.alertSeverity = alertSeverity;
	}

	@Override
	@XmlElement(name="rule-type", namespace="http://www.example.org/cviz-rule")
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	@Override
	@XmlElement(name="timestamp-pattern", namespace="http://www.example.org/cviz-rule")
	public void setReceiveTimePattern(String receiveTimePattern) {
		this.receiveTimePattern = receiveTimePattern;
	}

	@Override
	@XmlElementWrapper(name = "variables", namespace="http://www.example.org/cviz-rule")
	@XmlElement(name="variable", namespace="http://www.example.org/cviz-rule")
	public void setRuleVariables(List<CVizEventRuleVariable> ruleVariables) {
		this.ruleVariables = ruleVariables;
	}

	@Override
	@XmlElementWrapper(name = "fields", namespace="http://www.example.org/cviz-rule")
	@XmlElement(name="field", namespace="http://www.example.org/cviz-rule")
	public void setRuleFields(List<CVizEventRuleField> ruleFields) {
		this.ruleFields = ruleFields;
	}

	@Override
	@XmlElement(name="match-pattern", namespace="http://www.example.org/cviz-rule")
	public void setTrapSeparator(String trapSeparator) {
		this.trapSeparator = trapSeparator;
	}

	@Override
	@XmlElement(name="node-name", namespace="http://www.example.org/cviz-rule")
	public void setTrapConditions(String trapConditions) {
		this.trapConditions = trapConditions;
	}
}