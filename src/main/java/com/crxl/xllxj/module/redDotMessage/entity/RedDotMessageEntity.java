package com.crxl.xllxj.module.redDotMessage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RedDotMessageEntity {
	
	@Id
	String id; //唯一ID
	String userId;//用户ID
	int scope; //范围（哪个区域的红点） 1外观 2背包 3任务
	int specific; //范围下具体所属红点
	int contentId; //红点的id
	boolean readState; //红点读取状态
	
	public RedDotMessageEntity() {
		super();
	}

	public RedDotMessageEntity(String id, String userId, int scope, int specific, int contentId, boolean readState) {
		super();
		this.id = id;
		this.userId = userId;
		this.scope = scope;
		this.specific = specific;
		this.contentId = contentId;
		this.readState = readState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public int getSpecific() {
		return specific;
	}

	public void setSpecific(int specific) {
		this.specific = specific;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public boolean isReadState() {
		return readState;
	}

	public void setReadState(boolean readState) {
		this.readState = readState;
	}
	
}
