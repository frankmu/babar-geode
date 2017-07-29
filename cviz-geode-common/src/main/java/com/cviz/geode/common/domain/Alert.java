package com.cviz.geode.common.domain;

import java.io.Serializable;

public class Alert implements Serializable{

	private static final long serialVersionUID = 6691986158803858615L;

	private String id;
	private Integer severity = 1; //告警级别 （ critical，major，minor，warning，normal，unkonwn）
	private String matchPolicy; //匹配的预处理策略
	private Long alertTime; //从log文件里extract出来的timestamp
	private Long receiveTime; //收到或采集消息的时间，主要是为了计算系统在正常或高峰时处理一条告警所消耗的时间

	private String alertUID;  //告警唯一标识 （例如alertFamily+alertObj+alertMsg）
	private String alertFamily; //告警类别  (network,host,application,middleware,db,other)
	private String alertSubClass;//告警子类（syslog，snmp，netflow...）
	private String alertObj;//告警对象(ip or hostname)
	private String alertValue;//告警值 （感觉没啥用，暂时保留）
	private String alertMsg; //告警明细（处理后的message）
	private String sourceMsg; //源消息
	private String alertCondition; //告警条件（个人建议放在配置文件中）
	private String alertFrom;  //告警来源 （网管主机【agent】的IP或hostname）
	private String resourceID; //告警资源 （关联资源信息的外键）
	private Integer    alertTally; //告警次数
	private String   alertFirstTime; //告警最早产生时间 （压缩中的第一条的时间）
	private String   alertLastTime; //告警最近产生时间   （压缩后最近的时间）
	private String   faultTime;  //设备发生故障时间
	
	// faultTime < receiveTime < alertfirstTime 
	private String alertKey; //提取的标签，例如interface,linkup,linkdown...
	private String enrichInfo; // 丰富信息

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getMatchPolicy() {
		return matchPolicy;
	}

	public void setMatchPolicy(String matchPolicy) {
		this.matchPolicy = matchPolicy;
	}

	public String getAlertUID() {
		return alertUID;
	}

	public void setAlertUID(String alertUID) {
		this.alertUID = alertUID;
	}

	public String getAlertFamily() {
		return alertFamily;
	}

	public void setAlertFamily(String alertFamily) {
		this.alertFamily = alertFamily;
	}

	public String getSourceMsg() {
		return sourceMsg;
	}

	public void setSourceMsg(String sourceMsg) {
		this.sourceMsg = sourceMsg;
	}

	public String getFaultTime() {
		return faultTime;
	}

	public void setFaultTime(String faultTime) {
		this.faultTime = faultTime;
	}

	public Long getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Long getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(long alertTime) {
		this.alertTime = alertTime;
	}

	public String getAlertObj() {
		return alertObj;
	}

	public void setAlertObj(String alertObj) {
		this.alertObj = alertObj;
	}

	public String getAlertKey() {
		return alertKey;
	}

	public void setAlertKey(String alertKey) {
		this.alertKey = alertKey;
	}
}