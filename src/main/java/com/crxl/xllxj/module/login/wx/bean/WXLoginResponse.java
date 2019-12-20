package com.crxl.xllxj.module.login.wx.bean;

public class WXLoginResponse {

	String openid;// string 用户唯一标识
	String session_key;// 会话密钥
	String unionid;// string 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID
					// 机制说明。
	int errcode;// 错误码
	String errmsg;// 错误信息

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

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

}
