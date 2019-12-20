package com.crxl.xllxj.module.appEarance.bean;

public class ResBuy {
	
	int itemType; // 物品类型
	int itemId; // 物品ID

	public ResBuy() {
		
	}

	public ResBuy(int itemType, int itemId) {
		this.itemType = itemType;
		this.itemId = itemId;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

}
