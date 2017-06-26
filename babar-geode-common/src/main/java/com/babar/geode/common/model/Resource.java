package com.babar.geode.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

@Region("resource")
public class Resource implements Serializable{

	private static final long serialVersionUID = 4240801730435589341L;
	private static AtomicLong COUNTER = new AtomicLong(0L);

	@Id
	private String sys_id;

	private String id;
	private String type;
	private String subclass;
	private String parent;
	private String name;
	private String description;

	private String text;
	private Long number;
	private Date date;

	@PersistenceConstructor
	public Resource() {
		this.sys_id = String.valueOf(COUNTER.incrementAndGet());
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
