package com.crxl.xllxj.module.bag.baen;

public class RewardItem {
	
	String itemKey;
	int itemNum;
	
	public RewardItem() {
	}
	public RewardItem(String itemKey, int itemNum) {
		super();
		this.itemKey = itemKey;
		this.itemNum = itemNum;
	}
	public String getItemKey() {
		return itemKey;
	}
	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	
}
