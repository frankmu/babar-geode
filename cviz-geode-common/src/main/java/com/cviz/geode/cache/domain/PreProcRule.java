package com.cviz.geode.cache.domain;

import java.io.Serializable;

public class PreProcRule implements Serializable{

	private static final long serialVersionUID = 8911497633876784961L;

	private String ruleID; 				//策略编号 (自动生成)
	private Boolean active; 				//使用状态(true, false)
	private String ruleName; 			//策略名称(唯一)
	private String ruleType;				//策略类型(syslog，trap)
	private String ruleSeq;				//优先序号
	private String procMode;				//处理方式
	private String alertSeverity;		//告警级别
	private String getReceiveTimeFormat;	//regex: 接收消息时间的pattern
	private String ruleVariables;		//json: 定义从消息中提取出的变量
	private String ruleFields;			//json: 定义标准化入库字段

	private String syslogMatchPattern;	//regex: 匹配消息正文全匹配(syslog)
	private String syslogMatchNode;  	//regex: 匹配node名称(syslog)
	private String trapSeparator;		//regex: trap消息的分隔符
	private String trapConditions; 		//json: 匹配Trap消息的变量，最多可以有20个，格式：[index:regex, index:regex]

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
	public String getRuleSeq() {
		return ruleSeq;
	}
	public void setRuleSeq(String ruleSeq) {
		this.ruleSeq = ruleSeq;
	}
	public String getProcMode() {
		return procMode;
	}
	public void setProcMode(String procMode) {
		this.procMode = procMode;
	}
	public String getAlertSeverity() {
		return alertSeverity;
	}
	public void setAlertSeverity(String alertSeverity) {
		this.alertSeverity = alertSeverity;
	}
	public String getReceiveTimeFormat() {
		return getReceiveTimeFormat;
	}
	public void setReceiveTimeFormat(String getReceiveTimeFormat) {
		this.getReceiveTimeFormat = getReceiveTimeFormat;
	}
	public String getRuleVariables() {
		return ruleVariables;
	}
	public void setRuleVariables(String ruleVariables) {
		this.ruleVariables = ruleVariables;
	}
	public String getRuleFields() {
		return ruleFields;
	}
	public void setRuleFields(String ruleFields) {
		this.ruleFields = ruleFields;
	}
	public String getSyslogMatchPattern() {
		return syslogMatchPattern;
	}
	public void setSyslogMatchPattern(String syslogMatchPattern) {
		this.syslogMatchPattern = syslogMatchPattern;
	}
	public String getSyslogMatchNode() {
		return syslogMatchNode;
	}
	public void setSyslogMatchNode(String syslogMatchNode) {
		this.syslogMatchNode = syslogMatchNode;
	}
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