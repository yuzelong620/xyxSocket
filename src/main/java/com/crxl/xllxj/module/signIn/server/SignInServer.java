package com.crxl.xllxj.module.signIn.server;

import java.util.List;

import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.SignInCache;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.signIn.bean.ResSignIn;
import com.crxl.xllxj.module.signIn.entity.SignInEntity;
import com.crxl.xllxj.module.util.DateUtil;
import com.globalgame.auto.json.SignIn_Json;

public class SignInServer extends BaseService {

	public SignInEntity load(String userId) {
		SignInEntity signInEntity = signInDao.findByID(userId);
		if(signInEntity == null) {
			signInEntity = new SignInEntity(userId,0,0,1,0,0);
			signInDao.save(signInEntity);
		}
		return signInEntity;
	}

	/**
	 * 用户获取签到信息
	 */
	public void userReadSignInData(RequestMsg req) {
		SessionBean session = sessionService.getSession(req.getChannel());
		SignInEntity signInEntity = load(session.getId());
		if(signInEntity.getReStart() == 1) { //签到完成需要重新开始签到
			if(!DateUtil.isSameDate(signInEntity.getSignInTime(), System.currentTimeMillis())) { //检测日期
				signInEntity.setSignInCount(0);
				signInEntity.setSignInTime(0);
				signInEntity.setShouldDay(1);
				signInEntity.setValue(0);
				signInEntity.setReStart(0);
				signInDao.save(signInEntity);
			}
		}
		if(signInEntity.getValue() == 1) {
			examineDate(signInEntity);
		}
		ResSignIn resSignIn = new ResSignIn(signInEntity.getSignInCount(),signInEntity.getShouldDay());
		req.sendCurrentCommand(resSignIn);
	}
	
	/**
	 * 用户每日签到
	 */
	public void everydaySignIn(RequestMsg req) {
		SessionBean session = sessionService.getSession(req.getChannel());
		SignInEntity signInEntity = load(session.getId());
		SignInCache signInCache = JsonCacheManager.getCache(SignInCache.class);
		List<SignIn_Json> signIn_JsonList =  signInCache.getList();
		for (SignIn_Json signIn_Json : signIn_JsonList) {
			if(signInEntity.getValue() == 1) {
				req.sendInfoMessage("今日已经签到~");
				return;
			} else {
				if(signInEntity.getSignInCount() + 1 == signIn_Json.getId()){
					signInEntity.setSignInCount(signInEntity.getSignInCount() + 1);
					signInEntity.setSignInTime(System.currentTimeMillis());
					signInEntity.setValue(1);
				    if(signInEntity.getSignInCount() == 7) { //已经签到7天 需要重新开始
				    	signInEntity.setReStart(1);
				    }
				    rewardService.addResource(session,signIn_Json.getRewardList());  //增加奖励
				    ResSignIn resSignIn = new ResSignIn(signInEntity.getSignInCount(),
				    		signInEntity.getShouldDay(),signIn_Json.getRewardList());
				    signInDao.save(signInEntity);
				    req.sendCurrentCommand(resSignIn);
				    return;
				}
			}
		}
	}
	
	//检查日期
	private boolean examineDate(SignInEntity signInEntity) {
		long nowDate = System.currentTimeMillis();
		long time = signInEntity.getSignInTime();
		//不是同一天
		if(!DateUtil.isSameDate(nowDate, time)) {
			signInEntity.setShouldDay(signInEntity.getShouldDay() + 1);
			signInEntity.setValue(0);
			signInDao.save(signInEntity);
			return true;
		}
		return false;
	}
}
