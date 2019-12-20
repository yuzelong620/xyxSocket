package com.crxl.xllxj.module.room.bean;

import java.util.HashMap;
import java.util.Map;
import com.crxl.xllxj.module.player.entity.PlayerEntity;

public class RoomItemBean {

	public RoomItemBean() {
	}
	
	public RoomItemBean(PlayerEntity player) { 
		this.headPic=player.getHeadPic(); 
		this.gender=player.getGender(); 
		this.nickName=player.getNickName();
		this.signature=player.getSignature();
		this.userId=player.getUserId();
		this.currentDuanId=player.getCurrentDuanId();
		this.currentStarNum=player.getCurrentStarNum();
		this.title = player.getTitle();
		this.headRahmen = player.getHeadRahmen();
	}
	
	public RoomItemBean(PlayerEntity player,boolean isRobot,int currentDuanId,int currentStarNum) { 
		this(player);
		this.isRobot=isRobot;
		this.currentDuanId=currentDuanId;
		this.currentStarNum=currentStarNum;
	}
	
	public RoomItemBean(PlayerEntity player,boolean isRobot,int currentDuanId, int currentStarNum,int title, int headRahmen) {
		this(player);
		this.isRobot = isRobot;
		this.title = title;
		this.headRahmen = headRahmen;
		this.currentDuanId = currentDuanId;
		this.currentStarNum = currentStarNum;
		this.title = title;
		this.headRahmen = headRahmen;
	}

	String userId;//用户id
	String nickName = "";// 昵称 
	int gender;//默认为0 保密1男2女
	String signature="";//个性签名 默认为空
	String headPic;//头像
	boolean isRobot=false;
	boolean mvp; //是否mvp
	int title; //称号
	int headRahmen; //头像框
 
    Map<Integer,AnswerInfoBean> answerIds=new HashMap<>();//回答的id <---> 完成时间
    int getScore;//获得的分数
    
	int currentDuanId;
	int currentStarNum;
	
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

	public boolean isRobot() {
		return isRobot;
	}

	public void setRobot(boolean isRobot) {
		this.isRobot = isRobot;
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

	public Map<Integer, AnswerInfoBean> getAnswerIds() {
		return answerIds;
	}

	public void setAnswerIds(Map<Integer, AnswerInfoBean> answerIds) {
		this.answerIds = answerIds;
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
