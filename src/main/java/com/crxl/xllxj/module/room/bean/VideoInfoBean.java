package com.crxl.xllxj.module.room.bean;

import com.globalgame.auto.json.Video_Json;

public class VideoInfoBean {
	/**  封面::*/
	private String	videoPicUrl;
	/** 视频地址::*/
	private String	videoUrl; 
	/** 下半段视频封面::*/
	private String	lastVideoPicUrl;
	/** 下半段视频地址::*/
	private String	lastVideoUrl;
	
	public VideoInfoBean() { 
	}
	public VideoInfoBean(Video_Json video) {
		this.videoUrl = video.getVideoUrl();
		this.videoPicUrl =  video.getVideoPicUrl();
		this.lastVideoUrl=video.getLastVideoUrl();
		this.lastVideoPicUrl=video.getLastVideoPicUrl(); 
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
