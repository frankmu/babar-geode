package com.cviz.preprocess.rule.syslog;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.cviz.preprocess.rule.CVizPreProcessRuleField;
import com.cviz.preprocess.rule.CVizPreProcessRuleVariable;

@XmlRootElement(name="rule", namespace="http://www.example.org/cviz-rule")
public class CVizPreProcessSyslogXMLRule extends CVizPreProcessSyslogRule {

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
	@XmlElement(name="receivetime-format", namespace="http://www.example.org/cviz-rule")
	public void setReceiveTimeFormat(String receiveTimeFormat) {
		this.receiveTimeFormat = receiveTimeFormat;
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
	public void setSyslogMatchPattern(String syslogMatchPattern) {
		this.syslogMatchPattern = syslogMatchPattern;
	}

	@Override
	@XmlElement(name="node-name", namespace="http://www.example.org/cviz-rule")
	public void setNodeName(String syslogMatchNode) {
		this.syslogMatchNode = syslogMatchNode;
	}
}