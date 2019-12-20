package com.crxl.xllxj.module.sportsPeriod.entity;

public class SportsPeriodEntity {

	String id;
	String userId;
	int score;
	int duan;
	int sportsTime;

	public SportsPeriodEntity() {

	}

	public SportsPeriodEntity(String id, String userId, int score, int duan, int sportsTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.score = score;
		this.duan = duan;
		this.sportsTime = sportsTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDuan() {
		return duan;
	}

	public void setDuan(int duan) {
		this.duan = duan;
	}

	public int getSportsTime() {
		return sportsTime;
	}

	public void setSportsTime(int sportsTime) {
		this.sportsTime = sportsTime;
	}

	
}
