package com.crxl.xllxj.module.bag.mesasge;

import java.util.List;

import com.crxl.xllxj.module.bag.baen.BagItemBean; 

public class BagChangeMessage  {
	List<BagItemBean> items; 
	public BagChangeMessage() {
	}

	public BagChangeMessage(List<BagItemBean> items) {
		this.items = items;
	}

	public List<BagItemBean> getItems() {
		return items;
	}

	public void setItems(List<BagItemBean> items) {
		this.items = items;
	}

}
