package com.crxl.xllxj.module.bag.baen;

public class BagItemBean {

	public BagItemBean() {
	}

	public BagItemBean(Integer itemId, Integer itemNum) {
		this.itemId = itemId;
		this.itemNum = itemNum;
	}

	private Integer itemId;//物品id
	private Integer itemNum;//数量

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}
	

}
