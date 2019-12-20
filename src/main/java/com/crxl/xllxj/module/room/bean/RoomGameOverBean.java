package com.crxl.xllxj.module.room.bean;

import java.util.List;

public class RoomGameOverBean {

	public RoomGameOverBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	int mySumScore;// 我方总分
	int rivalSumScore;// 对方总分

	List<MemberBean> myItemInfo;// 我方信息
	List<MemberBean> rivalItemInfo;// 对方信息

	boolean myWin;
	int myScore;
	int currentDuanId;
	int currentStarNum;

	int addStartNum;// 增加星星数量
	int continuousWinNum;// 连胜次数

	boolean isMvp;// 是否为 mvp
	int addCoin;// 加金币

	List<QuestionAnswerInfoBean> questionAnswers;// 所有玩家的回答

	public RoomGameOverBean(int mySumScore, int rivalSumScore, List<RoomItemBean> myItemInfo,
			List<RoomItemBean> rivalItemInfo, boolean myWin, int myScore, int currentDuanId, int currentStarNum,
			int addStartNum, int continuousWinNum, List<QuestionAnswerInfoBean> questionAnswers, boolean isMvp,
			int addCoin) {
		this.mySumScore = mySumScore;
		this.rivalSumScore = rivalSumScore;
		this.myItemInfo = RoomBeanUtil.toMemberBean(myItemInfo);
		this.rivalItemInfo = RoomBeanUtil.toMemberBean(rivalItemInfo);
		this.myWin = myWin;
		this.myScore = myScore;
		this.currentDuanId = currentDuanId;
		this.currentStarNum = currentStarNum;
		this.continuousWinNum = continuousWinNum;
		this.questionAnswers = questionAnswers;
		this.addStartNum = addStartNum;
		this.isMvp = isMvp;
		this.addCoin = addCoin;
	}

	public boolean isMvp() {
		return isMvp;
	}

	public void setMvp(boolean isMvp) {
		this.isMvp = isMvp;
	}

	public int getAddCoin() {
		return addCoin;
	}

	public void setAddCoin(int addCoin) {
		this.addCoin = addCoin;
	}

	public List<QuestionAnswerInfoBean> getQuestionAnswers() {
		return questionAnswers;
	}

	public void setQuestionAnswers(List<QuestionAnswerInfoBean> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

	public int getContinuousWinNum() {
		return continuousWinNum;
	}

	public void setContinuousWinNum(int continuousWinNum) {
		this.continuousWinNum = continuousWinNum;
	}

	public int getAddStartNum() {
		return addStartNum;
	}

	public void setAddStartNum(int addStartNum) {
		this.addStartNum = addStartNum;
	}

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

	public int getMyScore() {
		return myScore;
	}

	public void setMyScore(int myScore) {
		this.myScore = myScore;
	}

	public int getMySumScore() {
		return mySumScore;
	}

	public void setMySumScore(int mySumScore) {
		this.mySumScore = mySumScore;
	}

	public int getRivalSumScore() {
		return rivalSumScore;
	}

	public void setRivalSumScore(int rivalSumScore) {
		this.rivalSumScore = rivalSumScore;
	}

	public List<MemberBean> getMyItemInfo() {
		return myItemInfo;
	}

	public void setMyItemInfo(List<MemberBean> myItemInfo) {
		this.myItemInfo = myItemInfo;
	}

	public List<MemberBean> getRivalItemInfo() {
		return rivalItemInfo;
	}

	public void setRivalItemInfo(List<MemberBean> rivalItemInfo) {
		this.rivalItemInfo = rivalItemInfo;
	}

	public boolean isMyWin() {
		return myWin;
	}

	public void setMyWin(boolean myWin) {
		this.myWin = myWin;
	}

}
