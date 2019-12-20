package com.crxl.xllxj.module.login.wx.bean;

public class WXOpenLoginResponse {
	String access_token;// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	long expires_in; // access_token接口调用凭证超时时间，单位（秒）
	String refresh_token;// 用户刷新access_token
	String openid;// 用户唯一标识
	String scope;// 用户授权的作用域，使用逗号（,）分隔
	int errcode;//如果返回的json中没有errcode 说明已经成功
	String errmsg="";
	
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
	
}
