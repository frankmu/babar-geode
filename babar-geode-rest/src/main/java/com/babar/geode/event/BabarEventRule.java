package com.babar.geode.event;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rule")
public class BabarEventRule {

	private String name;
	private int priority;
	private String action;
	private int serverity;
	private String nodeName;
	private String ruletype;
	private String matchPattern;
	private List<String> variables;
	private List<BabarEventRuleField> fields;

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	@XmlElement
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getAction() {
		return action;
	}

	@XmlElement
	public void setAction(String action) {
		this.action = action;
	}

	public int getServerity() {
		return serverity;
	}

	@XmlElement
	public void setServerity(int serverity) {
		this.serverity = serverity;
	}

	public String getNodeName() {
		return nodeName;
	}

	@XmlElement
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getRuletype() {
		return ruletype;
	}

	@XmlElement
	public void setRuletype(String ruletype) {
		this.ruletype = ruletype;
	}

	public String getMatchPattern() {
		return matchPattern;
	}

	@XmlElement
	public void setMatchPattern(String matchPattern) {
		this.matchPattern = matchPattern;
	}

	public List<String> getVairalbes() {
		return variables;
	}

	@XmlElementWrapper(name = "variables")
	@XmlElement(name="variable")
	public void setVairalbes(List<String> variables) {
		this.variables = variables;
	}

	public List<BabarEventRuleField> getFields() {
		return fields;
	}

	@XmlElementWrapper(name = "fields")
	@XmlElement(name="field")
	public void setFields(List<BabarEventRuleField> fields) {
		this.fields = fields;
	}
}