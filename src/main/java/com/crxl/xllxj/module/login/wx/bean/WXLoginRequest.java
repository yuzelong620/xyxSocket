package com.crxl.xllxj.module.login.wx.bean;

public class WXLoginRequest { 

	    private String appid; //应用唯一标识，在微信开放平台提交应用审核通过后获得
	    private String secret; //应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
	    private String code; //填写第一步获取的code参数
	    private String grant_type = "authorization_code";//填authorization_code
	    
	    public WXLoginRequest(String appid, String secret, String code, String grant_type) {
		 
			this.appid = appid;
			this.secret = secret;
			this.code = code;
			this.grant_type = grant_type;
		}
	    
	    
	    
		public WXLoginRequest() { 
		}
		public String getAppid() {
			return appid;
		}
		public void setAppid(String appid) {
			this.appid = appid;
		}
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getGrant_type() {
			return grant_type;
		}
		public void setGrant_type(String grant_type) {
			this.grant_type = grant_type;
		}
	    
	 

}
