package com.crxl.xllxj.module.identity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class IdentityEntity {
	@Id
	private int id;
	private String name;
	private long value;
	 
	 
	/**
	 * 
	 */
	public IdentityEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param name
	 * @param value
	 */
	public IdentityEntity(int id, String name, long value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
}
