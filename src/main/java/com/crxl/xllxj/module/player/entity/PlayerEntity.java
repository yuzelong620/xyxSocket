package com.crxl.xllxj.module.player.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PlayerEntity {

	public PlayerEntity() {
	}

	public PlayerEntity(String userId) {
		this.userId = userId;
		this.nickName = "";
		this.headPic="";
	}
	
	@Id
	String userId;//
	String nickName = "";// 昵称
	int goldCoin;//金币 
	int gender;//默认为0 保密1男2女
	String signature="";//个性签名 默认为空
	private String headPic;//头像 
	int detectiveGrade; //侦探等级
	int gameSeason; //当前赛季
	String province; //省份
	String city; //城市
	 
	int currentDuanId;// 当前段位
	int currentStarNum;//当前星星数
	
	int scoreSun; //积分排行榜
	
	int title; //称号
	int headRahmen; //头像框
	List<Integer> languageBag;//语言包
	

	int continuousWinNum;//连续胜利次
	int correctCount; //答对题总数
	int winCount; //总胜利数
	int gamesCount;//游戏总场次数
	int authorization; //是否授权 0 未授权  1 授权
	int defeatedNum; //失败总数
	
	public int getGamesCount() {
		return gamesCount;
	}

	public void setGamesCount(int gamesCount) {
		this.gamesCount = gamesCount;
	}

	public int getContinuousWinNum() {
		return continuousWinNum;
	}

	public void setContinuousWinNum(int continuousWinNum) {
		this.continuousWinNum = continuousWinNum;
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

	public String getSignature() {
		return signature;
	}
 
	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public int getGoldCoin() {
		return goldCoin;
	}

	public void setGoldCoin(int goldCoin) {
		this.goldCoin = goldCoin;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public int getDetectiveGrade() {
		return detectiveGrade;
	}

	public void setDetectiveGrade(int detectiveGrade) {
		this.detectiveGrade = detectiveGrade;
	}

	public int getGameSeason() {
		return gameSeason;
	}

	public void setGameSeason(int gameSeason) {
		this.gameSeason = gameSeason;
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

	public List<Integer> getLanguageBag() {
		return languageBag;
	}

	public void setLanguageBag(List<Integer> languageBag) {
		this.languageBag = languageBag;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCorrectCount() {
		return correctCount;
	}

	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
	}

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	public int getAuthorization() {
		return authorization;
	}

	public void setAuthorization(int authorization) {
		this.authorization = authorization;
	}

	public int getScoreSun() {
		return scoreSun;
	}

	public void setScoreSun(int scoreSun) {
		this.scoreSun = scoreSun;
	}

	public int getDefeatedNum() {
		return defeatedNum;
	}

	public void setDefeatedNum(int defeatedNum) {
		this.defeatedNum = defeatedNum;
	}
	
	

}