package com.globalgame.auto.json;
import java.util.List;
import com.mind.core.util.StringIntTuple;
import com.mind.core.util.IntDoubleTuple;
import com.mind.core.util.IntTuple;
import com.mind.core.util.ThreeTuple;
import com.mind.core.util.StringFloatTuple;

/**
*自动生成类
*/
public class GameParams_Json{
	/** 默认用户头像网络地址1::*/
	private String	userPic1;
	/** 默认用户头像网络地址2::*/
	private String	userPic2;
	/** 编号::*/
	private Integer	id;
	/** 房间总人数::*/
	private Integer	roomPersonNum;
	/** 答题添加的基础分数::*/
	private Integer	questionAddBaseScore;
	/** 答题添加的分数。 ::*/
	private Integer	questionAddScore;
	/** 答题间隔时间(单位秒)::*/
	private Integer	answerLimitTime;
	/** 房间中视频的数量::*/
	private Integer	roomVideoNum;
	/** 每个阶段间隔（单位秒）::*/
	private Integer	gameIntervalTime;
	/** 最大匹配时间（单位秒），这个时间无法匹配到人就加机器人::*/
	private Integer	maxMatchTime;
	/** 注册给的礼包::*/
	private List<StringIntTuple>	regGift;
	/** 给客户端的视频加载时间（单位毫秒）::*/
	private Integer	videoLoadTime;
	/** 短视频时间（单位秒）::*/
	private Integer	shortVideoTime;
	/** 短视频匹配玩家的 场次::*/
	private Integer	shortVideoGamesCount;
	/** 每场的每组得分基数N。 pscore/sumScore*n+ mvp*n=用户所得金币::*/
	private Integer	userGroupGetCoin;

	/** 默认用户头像网络地址1::*/
	public String getUserPic1(){
		return this.userPic1;
	}
	/** 默认用户头像网络地址2::*/
	public String getUserPic2(){
		return this.userPic2;
	}
	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 房间总人数::*/
	public Integer getRoomPersonNum(){
		return this.roomPersonNum;
	}
	/** 答题添加的基础分数::*/
	public Integer getQuestionAddBaseScore(){
		return this.questionAddBaseScore;
	}
	/** 答题添加的分数。 ::*/
	public Integer getQuestionAddScore(){
		return this.questionAddScore;
	}
	/** 答题间隔时间(单位秒)::*/
	public Integer getAnswerLimitTime(){
		return this.answerLimitTime;
	}
	/** 房间中视频的数量::*/
	public Integer getRoomVideoNum(){
		return this.roomVideoNum;
	}
	/** 每个阶段间隔（单位秒）::*/
	public Integer getGameIntervalTime(){
		return this.gameIntervalTime;
	}
	/** 最大匹配时间（单位秒），这个时间无法匹配到人就加机器人::*/
	public Integer getMaxMatchTime(){
		return this.maxMatchTime;
	}
	/** 注册给的礼包::*/
	public List<StringIntTuple> getRegGift(){
		return this.regGift;
	}
	/** 给客户端的视频加载时间（单位毫秒）::*/
	public Integer getVideoLoadTime(){
		return this.videoLoadTime;
	}
	/** 短视频时间（单位秒）::*/
	public Integer getShortVideoTime(){
		return this.shortVideoTime;
	}
	/** 短视频匹配玩家的 场次::*/
	public Integer getShortVideoGamesCount(){
		return this.shortVideoGamesCount;
	}
	/** 每场的每组得分基数N。 pscore/sumScore*n+ mvp*n=用户所得金币::*/
	public Integer getUserGroupGetCoin(){
		return this.userGroupGetCoin;
	}
	/**默认用户头像网络地址1::*/
	public void setUserPic1(String userPic1){
		this.userPic1 = userPic1;
	}
	/**默认用户头像网络地址2::*/
	public void setUserPic2(String userPic2){
		this.userPic2 = userPic2;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**房间总人数::*/
	public void setRoomPersonNum(Integer roomPersonNum){
		this.roomPersonNum = roomPersonNum;
	}
	/**答题添加的基础分数::*/
	public void setQuestionAddBaseScore(Integer questionAddBaseScore){
		this.questionAddBaseScore = questionAddBaseScore;
	}
	/**答题添加的分数。 ::*/
	public void setQuestionAddScore(Integer questionAddScore){
		this.questionAddScore = questionAddScore;
	}
	/**答题间隔时间(单位秒)::*/
	public void setAnswerLimitTime(Integer answerLimitTime){
		this.answerLimitTime = answerLimitTime;
	}
	/**房间中视频的数量::*/
	public void setRoomVideoNum(Integer roomVideoNum){
		this.roomVideoNum = roomVideoNum;
	}
	/**每个阶段间隔（单位秒）::*/
	public void setGameIntervalTime(Integer gameIntervalTime){
		this.gameIntervalTime = gameIntervalTime;
	}
	/**最大匹配时间（单位秒），这个时间无法匹配到人就加机器人::*/
	public void setMaxMatchTime(Integer maxMatchTime){
		this.maxMatchTime = maxMatchTime;
	}
	/**注册给的礼包::*/
	public void setRegGift(List<StringIntTuple> regGift){
		this.regGift = regGift;
	}
	/**给客户端的视频加载时间（单位毫秒）::*/
	public void setVideoLoadTime(Integer videoLoadTime){
		this.videoLoadTime = videoLoadTime;
	}
	/**短视频时间（单位秒）::*/
	public void setShortVideoTime(Integer shortVideoTime){
		this.shortVideoTime = shortVideoTime;
	}
	/**短视频匹配玩家的 场次::*/
	public void setShortVideoGamesCount(Integer shortVideoGamesCount){
		this.shortVideoGamesCount = shortVideoGamesCount;
	}
	/**每场的每组得分基数N。 pscore/sumScore*n+ mvp*n=用户所得金币::*/
	public void setUserGroupGetCoin(Integer userGroupGetCoin){
		this.userGroupGetCoin = userGroupGetCoin;
	}
}