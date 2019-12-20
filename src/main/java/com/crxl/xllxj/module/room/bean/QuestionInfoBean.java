package com.crxl.xllxj.module.room.bean;

import com.globalgame.auto.json.Video_Json;

public class QuestionInfoBean {
	
	public QuestionInfoBean() {
	}
	public QuestionInfoBean(Video_Json json,int number,int questionId) {
		 this.questionNumber=number;
		 this.lastVideoPicUrl=json.getLastVideoPicUrl();
		 this.lastVideoUrl=json.getLastVideoUrl();
		 this.videoid=json.getId();
		 this.videoPicUrl=json.getVideoPicUrl();
		 this.videoUrl=json.getVideoUrl();	
		 this.questionId=questionId;
	}
	
    int questionId;
    int questionNumber; 
	Integer	videoid; 
	String	videoPicUrl; 
	String	videoUrl;  
	String	lastVideoPicUrl; 
	String	lastVideoUrl;
	 
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public Integer getVideoid() {
		return videoid;
	}
	public void setVideoid(Integer videoid) {
		this.videoid = videoid;
	}
	public String getVideoPicUrl() {
		return videoPicUrl;
	}
	public void setVideoPicUrl(String videoPicUrl) {
		this.videoPicUrl = videoPicUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getLastVideoPicUrl() {
		return lastVideoPicUrl;
	}
	public void setLastVideoPicUrl(String lastVideoPicUrl) {
		this.lastVideoPicUrl = lastVideoPicUrl;
	}
	public String getLastVideoUrl() {
		return lastVideoUrl;
	}
	public void setLastVideoUrl(String lastVideoUrl) {
		this.lastVideoUrl = lastVideoUrl;
	}
	
}
