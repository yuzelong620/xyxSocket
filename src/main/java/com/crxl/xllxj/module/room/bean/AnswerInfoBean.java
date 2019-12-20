package com.crxl.xllxj.module.room.bean;
/**
 * 答題信息
 */
public class AnswerInfoBean {
	
    int questionId;
	int answerId;
    long time;
    boolean result;
    int addScore;
    
	public AnswerInfoBean() {
		
	}

	public AnswerInfoBean(int answerId, long time,int questionId,boolean result,int addScore ) {
		this.questionId=questionId;
		this.answerId = answerId;
		this.time = time;
		this.result=result; 
		this.addScore=addScore;
	}
	
	
	public int getAddScore() {
		return addScore;
	}


	public void setAddScore(int addScore) {
		this.addScore = addScore;
	}


	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
    
}
