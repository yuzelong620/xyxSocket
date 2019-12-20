
package com.crxl.xllxj.module.core.constent;

public interface ChannelCost {
	
	/** wx小游戏 登录地址 */
	public static final String wx_login_url = "https://api.weixin.qq.com/sns/jscode2session";
	/**微信开放平台获取用户信息*/
	public static final String wx_open_get_userinfo="https://api.weixin.qq.com/sns/userinfo";
	
	/** 授权类型 */
	public static final String xyx_grant_type = "authorization_code";	
	//小游戏
	public static final String xyx_appId = "wx4e8f599734057dc0";
	public static final String xyx_appSecret = "9b8a14dbe422d79409d7846869b33bb9";
	
	/** 小程序渠道id */
	public enum ChannelId{ 
		 test,
		 wx_xyx,
	}

}
