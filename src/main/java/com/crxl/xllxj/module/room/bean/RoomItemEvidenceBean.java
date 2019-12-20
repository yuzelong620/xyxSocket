package com.crxl.xllxj.module.room.bean;

public class RoomItemEvidenceBean {
	int siteId;// 搜证地点id
	int evidenceNum;// 证据数量

	public RoomItemEvidenceBean() { 
	}

	public RoomItemEvidenceBean(int siteId, int evidenceNum) { 
		this.siteId = siteId;
		this.evidenceNum = evidenceNum;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getEvidenceNum() {
		return evidenceNum;
	}

	public void setEvidenceNum(int evidenceNum) {
		this.evidenceNum = evidenceNum;
	}

}
