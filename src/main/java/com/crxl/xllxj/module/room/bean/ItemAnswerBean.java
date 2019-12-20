package com.crxl.xllxj.module.room.bean;

public class ItemAnswerBean {
	
    String userId;
    long time;
    boolean result; 
    int answerId;
    int score;
    
	public ItemAnswerBean() { 
	}


	public ItemAnswerBean(AnswerInfoBean bean,String userId) {
		this.userId = userId;
		this.time = bean.getTime();
		this.result = bean.isResult();
		this.answerId=bean.getAnswerId();
		this.score=bean.getAddScore();
	} 

	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public int getAnswerId() {
		return answerId;
	}



	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
