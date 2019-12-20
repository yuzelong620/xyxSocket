package com.crxl.xllxj.module.room.bean;

public class AnswerRes {
	
	boolean result;//答题结果
    int     addScore;//添加
    int     currentGetScore;//当前值
    String userId;//用户id
    int questionId;//问题id
    
	public AnswerRes() {
	}
	public AnswerRes(boolean result, int addScore, int currentGetScore, String userId, int questionId) {		 
		this.result = result;
		this.addScore = addScore;
		this.currentGetScore = currentGetScore;
		this.userId = userId;
		this.questionId = questionId;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
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
}
