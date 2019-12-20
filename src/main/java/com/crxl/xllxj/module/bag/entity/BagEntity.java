package com.crxl.xllxj.module.bag.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class BagEntity{
	
	public BagEntity() {
	}
	
	public BagEntity(String userId, Map<Integer, Integer> items) {
		this.userId = userId;
		this.items = items;
	}
	@Id
	String userId;//用户id
	Map<Integer,Integer> items=new HashMap<>();//物品项目 
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<Integer, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Integer, Integer> items) {
		this.items = items;
	}
	
	
}
