package com.cviz.geode.cache.domain;

import java.io.Serializable;

public class AlertHistory implements Serializable {

	private static final long serialVersionUID = -8240100537559081548L;

	private String alertUID; 		//告警唯一标识即流水号 (需包含时间)
	private String compressKey;		//压缩字段外键 alert表
	private String alertObj;			//告警对象 (ip or hostname)
	private String alertMsg;			//告警明细 (处理后的message)
	private String sourceMsg; 		//源消息
	private String alertFrom;  		//告警来源 (网管主机 [agent] 的IP或hostname)
	private Long faultTime;  		//设备发生故障时间
	private Long receiveTime; 		//收到或采集消息的时间，主要是为了计算系统在正常或高峰时处理一条告警所消耗的时间。faultTime < receiveTime < alertfirstTime 

	public String getAlertUID() {
		return alertUID;
	}
	public void setAlertUID(String alertUID) {
		this.alertUID = alertUID;
	}
	public String getCompressKey() {
		return compressKey;
	}
	public void setCompressKey(String compressKey) {
		this.compressKey = compressKey;
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
}
