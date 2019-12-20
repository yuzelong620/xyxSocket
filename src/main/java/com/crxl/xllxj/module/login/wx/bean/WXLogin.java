package com.crxl.xllxj.module.login.wx.bean;

public class WXLogin {

	String channelId;
	String code;
	
	public WXLogin() {
		
	}
	
	public WXLogin(String channelId, String code) {
		super();
		this.channelId = channelId;
		this.code = code;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
