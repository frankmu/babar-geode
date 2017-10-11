package com.cviz.preprocess.rule.trap;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.cviz.preprocess.rule.CVizPreProcessRuleCondition;
import com.cviz.preprocess.rule.CVizPreProcessRuleField;
import com.cviz.preprocess.rule.CVizPreProcessRuleVariable;

@XmlRootElement(name="rule", namespace="http://www.example.org/cviz-rule")
public class CVizPreProcessTrapXMLRule extends CVizPreProcessTrapRule {

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
	@XmlElement(name="timestamp-format", namespace="http://www.example.org/cviz-rule")
	public void setReceiveTimeFormat(String receiveTimeFormat) {
		this.receiveTimeFormat = receiveTimeFormat;
	}

	@Override
	@XmlElement(name="trap-receivetime-pattern", namespace="http://www.example.org/cviz-rule")
	public void setTrapReceiveTimePattern(String trapReceiveTimePattern) {
		this.trapReceiveTimePattern = trapReceiveTimePattern;
	}

	@Override
	@XmlElementWrapper(name = "variables", namespace="http://www.example.org/cviz-rule")
	@XmlElement(name="variable", namespace="http://www.example.org/cviz-rule")
	public void setRuleVariables(List<CVizPreProcessRuleVariable> ruleVariables) {
		this.ruleVariables = ruleVariables;
	}

	@Override
	@XmlElementWrapper(name = "fields", namespace="http://www.example.org/cviz-rule")
	@XmlElement(name="field", namespace="http://www.example.org/cviz-rule")
	public void setRuleFields(List<CVizPreProcessRuleField> ruleFields) {
		this.ruleFields = ruleFields;
	}

	@Override
	@XmlElement(name="match-pattern", namespace="http://www.example.org/cviz-rule")
	public void setTrapSeparator(String trapSeparator) {
		this.trapSeparator = trapSeparator;
	}

	@Override
	@XmlElementWrapper(name = "conditions", namespace="http://www.example.org/cviz-rule")
	@XmlElement(name="condition", namespace="http://www.example.org/cviz-rule")
	public void setTrapConditions(List<CVizPreProcessRuleCondition> trapConditions) {
		this.trapConditions = trapConditions;
	}
}