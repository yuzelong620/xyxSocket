package com.crxl.xllxj.module.room.bean;

public class MemberItem {
	private int questionId;
	private int answerId;
    private long time;
    private boolean result;
	 

	public MemberItem() { 
	}

	public MemberItem(int questionId, int answerId, long time, boolean result) {
		this.questionId = questionId;
		this.answerId = answerId;
		this.time = time;
		this.result = result;
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

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

}
