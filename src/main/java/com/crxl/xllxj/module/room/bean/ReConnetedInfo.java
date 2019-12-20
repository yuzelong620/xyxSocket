package com.crxl.xllxj.module.room.bean;

import com.crxl.xllxj.module.room.entity.RoomEntity;

public class ReConnetedInfo extends RoomBean {

	public ReConnetedInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReConnetedInfo(RoomEntity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	public ReConnetedInfo(RoomEntity entity, int leftScore, int rightScore, int currentQuestionNumber) {
		super(entity);
		this.leftScore = leftScore;
		this.rightScore = rightScore;
		this.currentQuestionNumber = currentQuestionNumber;
	}

	int leftScore;
	int rightScore;

	int currentQuestionNumber;// 当前第几题

	public int getLeftScore() {
		return leftScore;
	}

	public void setLeftScore(int leftScore) {
		this.leftScore = leftScore;
	}

	public int getRightScore() {
		return rightScore;
	}

	public void setRightScore(int rightScore) {
		this.rightScore = rightScore;
	}

	public int getCurrentQuestionNumber() {
		return currentQuestionNumber;
	}

	public void setCurrentQuestionNumber(int currentQuestionNumber) {
		this.currentQuestionNumber = currentQuestionNumber;
	}

}
