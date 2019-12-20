package com.crxl.xllxj.module.session.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.crxl.xllxj.module.bag.entity.BagEntity;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.json.datacache.GameParamsCache;
import com.crxl.xllxj.module.player.entity.PlayerEntity; 
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.session.manager.SessionManager;
import com.crxl.xllxj.module.task.entity.TaskEntity;
import com.mind.core.util.StringIntTuple;
import com.crxl.xllxj.module.user.entity.UserEntity;
import io.netty.channel.Channel;
import com.crxl.xllxj.module.core.net.MessageChannel;
@Service
public class SessionService extends BaseService{
 
	public void setSession(Channel channl, SessionBean session ){
		 SessionManager.instance.setSession(channl, session);
	}

	public SessionBean getSession(Channel channl) {
		return SessionManager.instance.getSession(channl);
	}
	
	public void onCloseChannel(Channel channel){
		SessionManager.instance.onCloseChannel(channel);
	}
	
	public void sendMessageToOnline(ResponseMsg msg){
		if(msg==null){
			logger.error("发送的信息为空！");
			return;
		}
		if(msg.getCommandId()==0){
			throw new RuntimeException("消息缺乏 cammand");
		}
        String jsonStr=JSONObject.toJSONString(msg);
		for( SessionBean session:SessionManager.getOnlineSessions()){ 
			try {
				MessageChannel.sendMessage(session.getChannel(), jsonStr);
			}
			catch (Exception e) {
				logger.error("发送广播信息错误。",e);
			}
		}
//		TaskManager.instance.putMessageLogger("在线广播信息，发出消息："+jsonStr);
	}
	@Deprecated
	public SessionBean createByPlayer(Channel channel, UserEntity user) {
		return createByPlayer(channel, user,false);
	}
	public SessionBean createByPlayer(Channel channel, UserEntity user,boolean isRegister){
		PlayerEntity player=playerService.load(user);
		String id=user.getId();
		BagEntity bagEntity=bagService.load(id);  
		String ip=channel.remoteAddress().toString();
		long lastSettleLoginTime=System.currentTimeMillis();
		Map<Integer,TaskEntity> taskMap = taskService.loadTaskList(id);
//		PlayerRoomEntity playerRoom = playerRoomService.load(id);
		SessionBean	sessionBean = new SessionBean(lastSettleLoginTime, id, channel, player, bagEntity, ip ,taskMap);
		SessionManager.instance.setSession(channel, sessionBean);// 存入管道
		logger.info("创建回话.ip:"+ip+",userId:"+id);
		if(isRegister){
			onRegiter(sessionBean); 
		}
		onCreateSession(sessionBean);
		return sessionBean;
	}
	
	/**当注册是调用*/
	private void onRegiter(SessionBean session) {
		List<StringIntTuple> stringIntTupleList = GameParamsCache.getGameParamsInstance().getRegGift();
		rewardService.addResource(session, stringIntTupleList);
	}
	
	/**当创建会话的时候 调用*/
	private void onCreateSession(SessionBean session){
		try{
			playerService.checkDetective(session); //侦探等级检测
			playerService.checkHolidayGift(session); //节日礼品检测
			playerService.checkGameSeason(session); //检测赛季
			playerService.checkAdorn(session); //检测佩戴
			bagService.sendBagInfo(session); //发送背包信息
			redDotMessageService.findRedDotMessageAll(session); //发送未读红点信息
		}
		catch(Exception e){
			logger.error("",e);
		}
	}
	public String getIp(Channel channel){
		String ip="";
		try{
			String str=channel.remoteAddress().toString();
			int start=str.lastIndexOf("/")+1;
			int ent=str.lastIndexOf(":");
			str=str.substring(start, ent);
			return str;
		}
		catch(Exception e){
			logger.error("",e);
		}
		return ip;
	}
	
	public SessionBean findByUserId(String userId){
		return SessionManager.instance.findByUserId(userId);
	}
	
}
