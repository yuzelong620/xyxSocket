package com.crxl.xllxj.module.login.wx.bean;

public class WXUserInfo {

	String avatarUrl; // 头像
	String city; // 城市
	String country; // 国家
	int gender; // 性别
	String language; // 语言
	String nickName; // 昵称
	String province; // 省份
	
	public WXUserInfo() {
		
	}

	public WXUserInfo(String avatarUrl, String city, String country, int gender, String language, String nickName,
			String province) {
		this.avatarUrl = avatarUrl;
		this.city = city;
		this.country = country;
		this.gender = gender;
		this.language = language;
		this.nickName = nickName;
		this.province = province;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
