package com.cviz.geode.rule;

public class CVizSyslogEventRule extends CVizEventRule {

	protected String syslogMatchPattern;	//regex: 匹配消息正文全匹配(syslog)
	protected String syslogMatchNode;  	//regex: 匹配node名称(syslog)

	public String getSyslogMatchPattern() {
		return syslogMatchPattern;
	}

	public void setSyslogMatchPattern(String syslogMatchPattern) {
		this.syslogMatchPattern = syslogMatchPattern;
	}

	public String getSyslogMatchNode() {
		return syslogMatchNode;
	}

	public void setNodeName(String syslogMatchNode) {
		this.syslogMatchNode = syslogMatchNode;
	}
}