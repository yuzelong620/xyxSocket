package com.crxl.xllxj.module.redDotMessage.bean;

public class RedDotMessageBean {

	int scope; // 范围 —（哪个区域的红点）
	int specific;// 范围下具体所属红点
	int contentId; // 范围下具体红点id

	public RedDotMessageBean() {
		super();
	}

	public RedDotMessageBean(int scope, int specific, int contentId) {
		super();
		this.scope = scope;
		this.specific = specific;
		this.contentId = contentId;
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
	
}
