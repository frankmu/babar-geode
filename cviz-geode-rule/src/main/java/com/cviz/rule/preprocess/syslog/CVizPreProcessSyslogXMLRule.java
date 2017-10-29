package com.cviz.rule.preprocess.syslog;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.cviz.rule.preprocess.CVizPreProcessRuleField;
import com.cviz.rule.preprocess.CVizPreProcessRuleVariable;

@XmlRootElement(name="rule", namespace="http://www.example.org/cviz-rule")
public class CVizPreProcessSyslogXMLRule extends CVizPreProcessSyslogRule {

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

	@Override
	@XmlElement(name="match-pattern", namespace="http://www.example.org/cviz-rule")
	public void setSyslogMatchPattern(String syslogMatchPattern) {
		this.syslogMatchPattern = syslogMatchPattern;
	}

	@Override
	@XmlElement(name="node-name", namespace="http://www.example.org/cviz-rule")
	public void setNodeName(String syslogMatchNode) {
		this.syslogMatchNode = syslogMatchNode;
	}
}