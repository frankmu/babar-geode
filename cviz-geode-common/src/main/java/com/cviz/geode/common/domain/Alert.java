package com.cviz.geode.common.domain;

import java.io.Serializable;

public class Alert implements Serializable{

	private static final long serialVersionUID = 6691986158803858615L;

	private String alertUID; 		//告警唯一标识即流水号 (需包含时间)
	private String alertFamily; 		//告警类别 (network,host,application,middleware,db,other)
	private String alertSubClass;	//告警子类 (syslog，snmp，netflow...)
	private String alertObj;			//告警对象 (ip or hostname)
	private String alertMsg;			//告警明细 (处理后的message)
	private String sourceMsg; 		//源消息
	private String alertFrom;  		//告警来源 (网管主机 [agent] 的IP或hostname)
	private Integer alertTally; 		//告警次数
	private Long alertFirstTime; 	//告警最早产生时间 (压缩中的第一条的时间)
	private Long alertLastTime; 		//告警最近产生时间 (压缩后最近的时间)
	private Long faultTime;  		//设备发生故障时间
	private Long receiveTime; 		//收到或采集消息的时间，主要是为了计算系统在正常或高峰时处理一条告警所消耗的时间。faultTime < receiveTime < alertfirstTime 
	private String alertStatus;		//告警状态 (产生，处理中，关闭)
	private Long alertCloseTime;		//告警关闭时间 (告警恢复时间)
	private Integer severity = 1; 	//告警级别 (critical，major，minor，warning，normal，unkonwn)
	private String alertKey; 		//提取的标签，例如interface, linkup, linkdown...
	private String matchPrePolicy; 	//匹配的预处理策略 (1对多)
	private String matchRelPolicy;	//匹配的关联处理策略 (1对多) (如果做关联高级处理的话)
	private String enrichInfo; 		//丰富信息
	private String recFlg;			//记录标识(正常和删除)
	private String resourceID; 		//告警资源 (关联资源信息的外键)

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
	public String getAlertSubClass() {
		return alertSubClass;
	}
	public void setAlertSubClass(String alertSubClass) {
		this.alertSubClass = alertSubClass;
	}
	public String getAlertObj() {
		return alertObj;
	}
	public void setAlertObj(String alertObj) {
		this.alertObj = alertObj;
	}
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
	public String getSourceMsg() {
		return sourceMsg;
	}
	public void setSourceMsg(String sourceMsg) {
		this.sourceMsg = sourceMsg;
	}
	public String getAlertFrom() {
		return alertFrom;
	}
	public void setAlertFrom(String alertFrom) {
		this.alertFrom = alertFrom;
	}
	public Integer getAlertTally() {
		return alertTally;
	}
	public void setAlertTally(Integer alertTally) {
		this.alertTally = alertTally;
	}
	public Long getAlertFirstTime() {
		return alertFirstTime;
	}
	public void setAlertFirstTime(Long alertFirstTime) {
		this.alertFirstTime = alertFirstTime;
	}
	public Long getAlertLastTime() {
		return alertLastTime;
	}
	public void setAlertLastTime(Long alertLastTime) {
		this.alertLastTime = alertLastTime;
	}
	public Long getFaultTime() {
		return faultTime;
	}
	public void setFaultTime(Long faultTime) {
		this.faultTime = faultTime;
	}
	public Long getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Long receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getAlertStatus() {
		return alertStatus;
	}
	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}
	public Long getAlertCloseTime() {
		return alertCloseTime;
	}
	public void setAlertCloseTime(Long alertCloseTime) {
		this.alertCloseTime = alertCloseTime;
	}
	public Integer getSeverity() {
		return severity;
	}
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	public String getAlertKey() {
		return alertKey;
	}
	public void setAlertKey(String alertKey) {
		this.alertKey = alertKey;
	}
	public String getMatchPrePolicy() {
		return matchPrePolicy;
	}
	public void setMatchPrePolicy(String matchPrePolicy) {
		this.matchPrePolicy = matchPrePolicy;
	}
	public String getMatchRelPolicy() {
		return matchRelPolicy;
	}
	public void setMatchRelPolicy(String matchRelPolicy) {
		this.matchRelPolicy = matchRelPolicy;
	}
	public String getEnrichInfo() {
		return enrichInfo;
	}
	public void setEnrichInfo(String enrichInfo) {
		this.enrichInfo = enrichInfo;
	}
	public String getRecFlg() {
		return recFlg;
	}
	public void setRecFlg(String recFlg) {
		this.recFlg = recFlg;
	}
	public String getResourceID() {
		return resourceID;
	}
	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}
}