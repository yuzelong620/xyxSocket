package com.crxl.xllxj.module.room.bean;

public class RoomAnswerReq {

	public RoomAnswerReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	int questionId;// 问题
	int answerId;// 答案

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
