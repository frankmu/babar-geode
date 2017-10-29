package com.cviz.rule.preprocess;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class CVizPreProcessRuleVariable implements Comparable<CVizPreProcessRuleVariable>{

	private String index;
	private String value;

	public String getIndex() {
		return index;
	}

	@XmlAttribute
	public void setIndex(String index) {
		this.index = index;
	}
	public String getValue() {
		return value;
	}

	@XmlValue
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(CVizPreProcessRuleVariable o) {
		return Integer.parseInt(index) - Integer.parseInt(o.getIndex());
	}
}
