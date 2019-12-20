package com.crxl.xllxj.module.appEarance.bean;

public class ItemAdorn {

	int atAdorn;
	int needAdorn;
	int articleType;

	public ItemAdorn() {

	}

	public ItemAdorn(int atAdorn, int needAdorn, int articleType) {
		this.atAdorn = atAdorn;
		this.needAdorn = needAdorn;
		this.articleType = articleType;
	}

	public int getAtAdorn() {
		return atAdorn;
	}

	public void setAtAdorn(int atAdorn) {
		this.atAdorn = atAdorn;
	}

	public int getNeedAdorn() {
		return needAdorn;
	}

	public void setNeedAdorn(int needAdorn) {
		this.needAdorn = needAdorn;
	}

	public int getArticleType() {
		return articleType;
	}

	public void setArticleType(int articleType) {
		this.articleType = articleType;
	}

}
