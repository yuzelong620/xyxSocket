package com.crxl.xllxj.module.room.bean;

public class RoomPlayerChange {

	String oldId;
	String newId;
	
	public RoomPlayerChange() {
		super();
	}
	public RoomPlayerChange(String oldId, String newId) {
		super();
		this.oldId = oldId;
		this.newId = newId;
	}
	public String getOldId() {
		return oldId;
	}
	public void setOldId(String oldId) {
		this.oldId = oldId;
	}
	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	};
	
	
	
}
