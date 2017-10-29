package com.cviz.rule.preprocess.trap;

import java.util.List;

import com.cviz.rule.preprocess.CVizPreProcessRule;
import com.cviz.rule.preprocess.CVizPreProcessRuleCondition;

public class CVizPreProcessTrapRule extends CVizPreProcessRule {

	protected String trapSeparator;							//regex: trap消息的分隔符
	protected String trapReceiveTimePattern;					//regex: trap time pattern
	protected List<CVizPreProcessRuleCondition> trapConditions; 	//定义trap消息条件，所有条件都需要满足才能视为match这条rule

	public String getTrapSeparator() {
		return trapSeparator;
	}
	public void setTrapSeparator(String trapSeparator) {
		this.trapSeparator = trapSeparator;
	}
	public List<CVizPreProcessRuleCondition> getTrapConditions() {
		return trapConditions;
	}
	public void setTrapConditions(List<CVizPreProcessRuleCondition> trapConditions) {
		this.trapConditions = trapConditions;
	}
	public String getTrapReceiveTimePattern() {
		return trapReceiveTimePattern;
	}
	public void setTrapReceiveTimePattern(String trapReceiveTimePattern) {
		this.trapReceiveTimePattern = trapReceiveTimePattern;
	}
}