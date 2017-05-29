package com.babar.geode.rest.model;

import java.io.Serializable;
import java.util.Date;
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

	private String id;
	private String type;
	private String subclass;
	private String object;
	private String value;
	private String details;
	private String condition;
	private String source;
	private String resource;
	private String count;
	private Date firstTime;
	private Date lastTime;
	
	private String text;
	private Long number;
	private Date date;

	@PersistenceConstructor
	public Alert() {
		this.sys_id = COUNTER.incrementAndGet();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
