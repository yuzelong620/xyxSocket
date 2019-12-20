package com.crxl.xllxj.module.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserEntity {
	@Id
	private String id;
	@Indexed(unique = true)
	private String openid = "";// 微信-游戏的唯一id
	@Indexed(unique = true)
	private String phone = "";// 手机号（唯一）
	private int isBindPhone = 0;// 1为已经绑定 手机
	private int isBindWx = 0;// 1为已经绑定 微信	
	private String sessionKey = "";// 微信回话key
	@Indexed
	private String unionid = "";
	private String channelId = "";
	@Indexed
	private String token="";
	private long loginTime = 0;
	private String deviceId = "";// 机器id
    private long identityId;

	public UserEntity(){
	}
	/**
	 * 手机登录
	 * @param id
	 * @param channel
	 * @param phone
	 */
	public UserEntity(String id,String channel,String phone,long identityId){
		this.id=id;
		this.channelId=channel;		
		this.phone=phone;
		this.isBindPhone=1;//标记手机已经绑定 
		this.openid=phone;//绑定的微信号，默认为设置  手机号		
		this.identityId=identityId;//自增序列id
		this.loginTime = System.currentTimeMillis();
	}

	

	public long getIdentityId() {
		return identityId;
	}
	public void setIdentityId(long identityId) {
		this.identityId = identityId;
	}
	/**
	 * 微信登录
	 * 
	 * @param id
	 * @param openid
	 * @param sessionKey
	 * @param unionid
	 * @param channel
	 */
	public UserEntity(String id, String openid, String sessionKey, String unionid, String channel,long identityId) {
		this.id = id;
		this.openid = openid;
		this.sessionKey = sessionKey;
		this.unionid = unionid;
		this.channelId = channel;
		
		this.isBindWx=1;//标志已经绑定微信 
	    this.phone=id;
		this.loginTime = System.currentTimeMillis();
	    this.identityId=identityId;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getIsBindPhone() {
		return isBindPhone;
	}

	public void setIsBindPhone(int isBindPhone) {
		this.isBindPhone = isBindPhone;
	}

	public int getIsBindWx() {
		return isBindWx;
	}

	public void setIsBindWx(int isBindWx) {
		this.isBindWx = isBindWx;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	
}
