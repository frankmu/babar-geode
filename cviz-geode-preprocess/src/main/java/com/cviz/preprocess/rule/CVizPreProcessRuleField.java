package com.cviz.preprocess.rule;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class CVizPreProcessRuleField {

	private String key;
	private String value;

	public CVizPreProcessRuleField(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	@XmlAttribute
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}

	@XmlValue
	public void setValue(String value) {
		this.value = value;
	}
}