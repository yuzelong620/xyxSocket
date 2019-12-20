package com.crxl.xllxj.module.room.bean;

public class NoticeAnswerInfoBean {
	String userId;
	int questionId;
	int addScore;
	int currentGetScore;
	boolean result;
	boolean isLeft=false;
	
	public NoticeAnswerInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeAnswerInfoBean(String userId, int questionId, int addScore, boolean result,int currentGetScore,boolean isLeft) { 
		this.userId = userId;
		this.questionId = questionId;
		this.addScore = addScore;
		this.result = result;
		this.currentGetScore=currentGetScore;
		this.isLeft=isLeft;
	}
	
	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	 
	public int getAddScore() {
		return addScore;
	}
	public void setAddScore(int addScore) {
		this.addScore = addScore;
	}
	public int getCurrentGetScore() {
		return currentGetScore;
	}
	public void setCurrentGetScore(int currentGetScore) {
		this.currentGetScore = currentGetScore;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
