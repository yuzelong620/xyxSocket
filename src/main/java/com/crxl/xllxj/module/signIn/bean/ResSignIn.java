package com.crxl.xllxj.module.signIn.bean;

import java.util.List;

import com.mind.core.util.StringIntTuple;

public class ResSignIn {

	int signInCount;
	int shouldDay;
	List<StringIntTuple> awardList;

	public ResSignIn() {
		
	}

	public ResSignIn(int signInCount, int shouldDay) {
		super();
		this.signInCount = signInCount;
		this.shouldDay = shouldDay;
	}

	public ResSignIn(int signInCount, int shouldDay, List<StringIntTuple> awardList) {
		super();
		this.signInCount = signInCount;
		this.shouldDay = shouldDay;
		this.awardList = awardList;
	}

	public int getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}

	public int getShouldDay() {
		return shouldDay;
	}

	public void setShouldDay(int shouldDay) {
		this.shouldDay = shouldDay;
	}

	public List<StringIntTuple> getAwardList() {
		return awardList;
	}

	public void setAwardList(List<StringIntTuple> awardList) {
		this.awardList = awardList;
	}
	
	
}
