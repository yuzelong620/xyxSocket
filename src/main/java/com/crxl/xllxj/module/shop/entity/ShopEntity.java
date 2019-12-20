package com.crxl.xllxj.module.shop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShopEntity {

	@Id
	String id;
	String userId; // 玩家ID
	int articleId; // 物品ID
	int obtainCount; // 获得的数量

	public ShopEntity() {

	}

	public ShopEntity(String id, String userId, int articleId, int obtainCount) {
		super();
		this.id = id;
		this.userId = userId;
		this.articleId = articleId;
		this.obtainCount = obtainCount;
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

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getObtainCount() {
		return obtainCount;
	}

	public void setObtainCount(int obtainCount) {
		this.obtainCount = obtainCount;
	}

}
