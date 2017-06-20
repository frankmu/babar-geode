package com.babar.geode.rule;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rule", namespace="http://www.example.org/babar-rule")
public class BabarEventRule {

	private String name;
	private int priority;
	private String action;
	private int severity;
	private String nodeName;
	private String ruleType;
	private String matchPattern;
	private List<String> variables;
	private List<BabarEventRuleField> fields;

	public String getName() {
		return name;
	}

	@XmlElement(namespace="http://www.example.org/babar-rule")
	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	@XmlElement(namespace="http://www.example.org/babar-rule")
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getAction() {
		return action;
	}

	@XmlElement(namespace="http://www.example.org/babar-rule")
	public void setAction(String action) {
		this.action = action;
	}

	public int getSeverity() {
		return severity;
	}

	@XmlElement(namespace="http://www.example.org/babar-rule")
	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getNodeName() {
		return nodeName;
	}

	@XmlElement(name="node-name", namespace="http://www.example.org/babar-rule")
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getRuleType() {
		return ruleType;
	}

	@XmlElement(name="rule-type", namespace="http://www.example.org/babar-rule")
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getMatchPattern() {
		return matchPattern;
	}

	@XmlElement(name="match-pattern", namespace="http://www.example.org/babar-rule")
	public void setMatchPattern(String matchPattern) {
		this.matchPattern = matchPattern;
	}

	public List<String> getVairalbes() {
		return variables;
	}

	@XmlElementWrapper(name = "variables", namespace="http://www.example.org/babar-rule")
	@XmlElement(name="variable", namespace="http://www.example.org/babar-rule")
	public void setVairalbes(List<String> variables) {
		this.variables = variables;
	}

	public List<BabarEventRuleField> getFields() {
		return fields;
	}

	@XmlElementWrapper(name = "fields", namespace="http://www.example.org/babar-rule")
	@XmlElement(name="field", namespace="http://www.example.org/babar-rule")
	public void setFields(List<BabarEventRuleField> fields) {
		this.fields = fields;
	}
}