package com.crxl.xllxj.module.signIn.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SignInEntity {

	@Id
	String userId;
	int signInCount; // 签到次数
	long signInTime; // 每次签到时间
	int shouldDay; //应该签到第几天
	int value; // 今天是否签到
	int reStart; //是否可以重新开始 0不可以 1可以
	
	public SignInEntity() {
		
	}

	public SignInEntity(String userId, int signInCount, long signInTime, int shouldDay, int value, int reStart) {
		super();
		this.userId = userId;
		this.signInCount = signInCount;
		this.signInTime = signInTime;
		this.shouldDay = shouldDay;
		this.value = value;
		this.reStart = reStart;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}

	public long getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(long signInTime) {
		this.signInTime = signInTime;
	}

	public int getShouldDay() {
		return shouldDay;
	}

	public void setShouldDay(int shouldDay) {
		this.shouldDay = shouldDay;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getReStart() {
		return reStart;
	}

	public void setReStart(int reStart) {
		this.reStart = reStart;
	}

}
