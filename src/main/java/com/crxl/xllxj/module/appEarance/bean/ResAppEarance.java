package com.crxl.xllxj.module.appEarance.bean;

public class ResAppEarance {

	int articleId; // 物品id
	int state; // 是否可以获得 0不可以 1可以 2领取过了
	int rank; //排序
	
	public ResAppEarance() {

	}

	public ResAppEarance(int articleId, int state, int rank) {
		super();
		this.articleId = articleId;
		this.state = state;
		this.rank = rank;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
