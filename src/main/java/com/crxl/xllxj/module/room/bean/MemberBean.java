package com.crxl.xllxj.module.room.bean;
public class MemberBean {

	public MemberBean() { 
	}
	public MemberBean(RoomItemBean bean) {
		this.headPic = bean.getHeadPic();
		this.gender = bean.getGender();
		this.nickName = bean.getNickName();
		this.signature = bean.getSignature();
		this.userId = bean.getUserId();
		this.getScore = bean.getGetScore();
		this. currentDuanId=bean.getCurrentDuanId();
		this. currentStarNum=bean.getCurrentStarNum();
		this.mvp = bean.isMvp();
		this.title = bean.getTitle();
		this.headRahmen = bean.getHeadRahmen();
	}
	int currentDuanId ;
	int currentStarNum ;
	String userId;// 用户id
	String nickName = "";// 昵称
	int gender;// 默认为0 保密1男2女
	String signature = "";// 个性签名 默认为空
	String headPic;// 头像
	int getScore;// 获得的分数 
	boolean mvp; //是否mvp
	int title; //称号
	int headRahmen; //头像框
	
	public int getCurrentDuanId() {
		return currentDuanId;
	}
	public void setCurrentDuanId(int currentDuanId) {
		this.currentDuanId = currentDuanId;
	}
	public int getCurrentStarNum() {
		return currentStarNum;
	}
	public void setCurrentStarNum(int currentStarNum) {
		this.currentStarNum = currentStarNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public int getGetScore() {
		return getScore;
	}
	public void setGetScore(int getScore) {
		this.getScore = getScore;
	}
	public boolean isMvp() {
		return mvp;
	}
	public void setMvp(boolean mvp) {
		this.mvp = mvp;
	}
	public int getTitle() {
		return title;
	}
	public void setTitle(int title) {
		this.title = title;
	}
	public int getHeadRahmen() {
		return headRahmen;
	}
	public void setHeadRahmen(int headRahmen) {
		this.headRahmen = headRahmen;
	}

}
