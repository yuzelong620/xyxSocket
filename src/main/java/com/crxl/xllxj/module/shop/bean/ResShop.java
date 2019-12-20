package com.crxl.xllxj.module.shop.bean;

public class ResShop {

	int articleId; // 物品ID
	int obtainCount; // 获得次数

	public ResShop() {

	}

	public ResShop(int articleId, int obtainCount) {
		super();
		this.articleId = articleId;
		this.obtainCount = obtainCount;
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

	@Override
	public String toString() {
		return "ResShop [articleId=" + articleId + ", obtainCount=" + obtainCount + "]";
	}
	
}
