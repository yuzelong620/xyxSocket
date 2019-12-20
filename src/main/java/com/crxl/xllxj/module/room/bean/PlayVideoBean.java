package com.crxl.xllxj.module.room.bean;

import com.globalgame.auto.json.Video_Json;

public class PlayVideoBean {
	
	/**  封面::*/
	private String	videoPicUrl;
	/** 视频地址::*/
	private String	videoUrl; 
	/** 下半段视频封面::*/
	private String	lastVideoPicUrl;
	/** 下半段视频地址::*/
	private String	lastVideoUrl;
	/**
	 * 视频播放截止时间
	 */
	private long endTime;
	
	public PlayVideoBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PlayVideoBean(Video_Json video,long endTime) {
		this.videoUrl = video.getVideoUrl();
		this.videoPicUrl =  video.getVideoPicUrl();
		this.lastVideoUrl=video.getLastVideoUrl();
		this.lastVideoPicUrl=video.getLastVideoPicUrl();
		this.endTime=endTime;
	}
	
	
	public long getEndTime() {
		return endTime;
	}


	public void setEndTime(long endTime) {
		this.endTime = endTime;
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
	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoPicUrl() {
		return videoPicUrl;
	}

	public void setVideoPicUrl(String videoPicUrl) {
		this.videoPicUrl = videoPicUrl;
	}	

}
