package com.crxl.xllxj.module.login.wx.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.crxl.xllxj.module.common.enums.EnumConstant;
import com.crxl.xllxj.module.core.constent.ChannelCost;
import com.crxl.xllxj.module.core.constent.ChannelCost.ChannelId;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.login.wx.bean.WXLogin;
import com.crxl.xllxj.module.login.wx.bean.WXLoginRequest;
import com.crxl.xllxj.module.login.wx.bean.WXLoginResponse;
import com.crxl.xllxj.module.login.wx.bean.WXUserInfo;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.user.entity.UserEntity;
import com.crxl.xllxj.module.util.HttpClientUtils;

@Service
public class WXService extends BaseService {
    
	/**
	 * 拼接成唯一id
	 */
	private String createId(String channelId, String openid) {
		return channelId + "_" + openid;
	}
	
	private WXLoginResponse getUnionId(String code) {
		WXLoginRequest accessTokenRequest = new WXLoginRequest(ChannelCost.xyx_appId, ChannelCost.xyx_appSecret, code,
				ChannelCost.xyx_grant_type);
		 return getUnionId(code,accessTokenRequest);
	}
	
	public WXLoginResponse getUnionId(String code,WXLoginRequest accessTokenRequest) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("appid", accessTokenRequest.getAppid());
		params.put("js_code", accessTokenRequest.getCode());
		params.put("grant_type", accessTokenRequest.getGrant_type());
		params.put("secret", accessTokenRequest.getSecret());
		String result = HttpClientUtils.sendGet(ChannelCost.wx_login_url, params);
		WXLoginResponse res = JSONObject.parseObject(result, WXLoginResponse.class);
		return res;
	}
	
	public WXLoginRequest getWxLoginRequest(String code,String channelId){
		if(channelId.equals(ChannelId.wx_xyx.name())){
	        return  new WXLoginRequest(ChannelCost.xyx_appId, ChannelCost.xyx_appSecret, code,ChannelCost.xyx_grant_type);
	    }
		return null;
	}
	
	/**
	 * 微信小游戏授权登录
	 */
	public void wxLogin(RequestMsg req) {
		WXLogin wxLogin = req.getBody(WXLogin.class);
		if (wxLogin.getChannelId() == null || "".equals(wxLogin.getChannelId())) {
			req.sendInfoMessage("登录异常");
			return;
		}
		if (null == wxLogin.getCode() || wxLogin.getCode().equals("")) {
			req.sendInfoMessage("code is null");
			return;
		}
		WXLoginRequest wxLoginReq = getWxLoginRequest(wxLogin.getCode(), wxLogin.getChannelId());
		if(wxLoginReq==null){
			req.sendInfoMessage("非法参数");
			return;
		}
		WXLoginResponse response = getUnionId(wxLogin.getCode());
		logger.info("wx登录返回："+JSONObject.toJSONString(response));

		if (response == null) {
			req.sendInfoMessage("根据得到的参数，访问微信失败");
			return;
		}
		if (response.getErrcode() == -1) {
			req.sendInfoMessage("微信服务器繁忙");
			return;
		}
		if (response.getErrcode() != 0) {
			req.sendInfoMessage("登录验证失败");
			return;
		}
		String userId = createId(wxLogin.getChannelId(), response.getOpenid());

		UserEntity userEntity = userDao.findByID(userId);
		boolean isRegister=false;
		if (userEntity == null) {
			long identityId = identityDao.nextId(EnumConstant.IdentityId.user);
			userEntity = new UserEntity(userId, response.getOpenid(), response.getSession_key(), response.getUnionid(),wxLogin.getChannelId(),identityId);
			userDao.save(userEntity);
			isRegister=true;
		}
		SessionBean sessionBean = sessionService.createByPlayer(req.getChannel(),userEntity,isRegister);
		loginService.sendSessionInfo(req, sessionBean);
	}
	
	/**
	 * 修改用户信息
	 */
	public void updateUserInfo(RequestMsg req) {
		WXUserInfo userInfo = req.getBody(WXUserInfo.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		PlayerEntity playerEntity = session.getPlayerEntity();
		playerEntity.setHeadPic(userInfo.getAvatarUrl());
		playerEntity.setCity(userInfo.getCity());
		playerEntity.setGender(userInfo.getGender());
		playerEntity.setNickName(userInfo.getNickName());
		playerEntity.setProvince(userInfo.getProvince());
		playerEntity.setAuthorization(1); //授权信息
		playerDao.save(playerEntity);
		req.sendCurrentCommand(playerEntity);
	}
	
}
