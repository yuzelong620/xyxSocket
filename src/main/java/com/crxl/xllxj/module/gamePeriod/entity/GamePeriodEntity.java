package com.crxl.xllxj.module.gamePeriod.entity;

public class GamePeriodEntity {

	int period; // 第几赛季
	int atPeriodTime; // 当前赛季时间 yyyyMm

	public GamePeriodEntity() {
		
	}

	public GamePeriodEntity(int period, int atPeriodTime) {
		this.period = period;
		this.atPeriodTime = atPeriodTime;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getAtPeriodTime() {
		return atPeriodTime;
	}

	public void setAtPeriodTime(int atPeriodTime) {
		this.atPeriodTime = atPeriodTime;
	}

}
