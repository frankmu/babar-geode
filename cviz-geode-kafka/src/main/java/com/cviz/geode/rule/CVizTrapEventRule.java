package com.cviz.geode.rule;

public class CVizTrapEventRule extends CVizEventRule {

	protected String trapSeparator;		//regex: trap消息的分隔符
	protected String trapConditions; 	//json: 匹配Trap消息的变量，最多可以有20个，格式：[index:regex, index:regex]

	public String getTrapSeparator() {
		return trapSeparator;
	}
	public void setTrapSeparator(String trapSeparator) {
		this.trapSeparator = trapSeparator;
	}
	public String getTrapConditions() {
		return trapConditions;
	}
	public void setTrapConditions(String trapConditions) {
		this.trapConditions = trapConditions;
	}
}