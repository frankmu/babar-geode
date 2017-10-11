package com.cviz.geode.rule;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class CVizEventRuleVariable implements Comparable<CVizEventRuleVariable>{

	private String index;
	private String value;

	public CVizEventRuleVariable(String index, String value) {
		this.index = index;
		this.value = value;
	}

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
	public int compareTo(CVizEventRuleVariable o) {
		return Integer.parseInt(index) - Integer.parseInt(o.getIndex());
	}
}
