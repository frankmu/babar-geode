package com.babar.geode.rest.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

@Region("alert")
public class Alert implements Serializable{

	private static final long serialVersionUID = 6691986158803858615L;
	private static AtomicLong COUNTER = new AtomicLong(0L);

	@Id
	private Long sys_id;

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
	private int    alertTally; //告警次数
	private long   alertFirstTime; //告警最早产生时间 （压缩中的第一条的时间）
	private long   alertLastTime; //告警最近产生时间   （压缩后最近的时间）
	private long   faultTime;  //设备发生故障时间
	private long   receiveTime; //收到或采集消息的时间，主要是为了计算系统在正常或高峰时处理一条告警所消耗的时间
	// faultTime < receiveTime < alertfirstTime 
	private int severity = 1; //告警级别 （ critical，major，minor，warning，normal，unkonwn）
	private String alertKey; //提取的标签，例如interface,linkup,linkdown...
	private String matchPolicy; // 匹配的预处理策略 （1对多）
	private String enrichInfo; // 丰富信息

	@PersistenceConstructor
	public Alert() {
		this.sys_id = COUNTER.incrementAndGet();
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
}
