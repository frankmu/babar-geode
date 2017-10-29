package com.cviz.rule.preprocess;

import java.util.List;

public abstract class CVizPreProcessRule {
	
	protected String ruleID; 							//策略编号 (自动生成)
	protected Boolean active; 							//使用状态(true, false)
	protected String ruleName; 							//策略名称(唯一)
	protected String ruleType;							//策略类型(syslog，trap)
	protected int ruleSeq;								//优先序号
	protected String procMode;							//处理方式
	protected int alertSeverity;							//告警级别
	protected String receiveTimeFormat;					//接收消息时间的pattern: Java date standard format
	protected List<CVizPreProcessRuleVariable> ruleVariables;	//定义从消息中提取出的变量
	protected List<CVizPreProcessRuleField> ruleFields;		//定义标准化入库字段

	public String getRuleID() {
		return ruleID;
	}
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public int getRuleSeq() {
		return ruleSeq;
	}
	public void setRuleSeq(int ruleSeq) {
		this.ruleSeq = ruleSeq;
	}
	public String getProcMode() {
		return procMode;
	}
	public void setProcMode(String procMode) {
		this.procMode = procMode;
	}
	public int getAlertSeverity() {
		return alertSeverity;
	}
	public void setAlertSeverity(int alertSeverity) {
		this.alertSeverity = alertSeverity;
	}
	public String getReceiveTimeFormat() {
		return receiveTimeFormat;
	}
	public void setReceiveTimeFormat(String receiveTimeFormat) {
		this.receiveTimeFormat = receiveTimeFormat;
	}
	public List<CVizPreProcessRuleVariable> getRuleVariables() {
		return ruleVariables;
	}
	public void setRuleVariables(List<CVizPreProcessRuleVariable> ruleVariables) {
		this.ruleVariables = ruleVariables;
	}
	public List<CVizPreProcessRuleField> getRuleFields() {
		return ruleFields;
	}
	public void setRuleFields(List<CVizPreProcessRuleField> ruleFields) {
		this.ruleFields = ruleFields;
	}
}