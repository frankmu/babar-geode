package com.cviz.geode.rule.trap;

import java.util.List;

import com.cviz.geode.rule.CVizEventRule;
import com.cviz.geode.rule.CVizEventRuleCondition;

public class CVizTrapEventRule extends CVizEventRule {

	protected String trapSeparator;							//regex: trap消息的分隔符
	protected String trapReceiveTimePattern;					//regex: trap time pattern
	protected List<CVizEventRuleCondition> trapConditions; 	//定义trap消息条件，所有条件都需要满足才能视为match这条rule

	public String getTrapSeparator() {
		return trapSeparator;
	}
	public void setTrapSeparator(String trapSeparator) {
		this.trapSeparator = trapSeparator;
	}
	public List<CVizEventRuleCondition> getTrapConditions() {
		return trapConditions;
	}
	public void setTrapConditions(List<CVizEventRuleCondition> trapConditions) {
		this.trapConditions = trapConditions;
	}
	public String getTrapReceiveTimePattern() {
		return trapReceiveTimePattern;
	}
	public void setTrapReceiveTimePattern(String trapReceiveTimePattern) {
		this.trapReceiveTimePattern = trapReceiveTimePattern;
	}
}