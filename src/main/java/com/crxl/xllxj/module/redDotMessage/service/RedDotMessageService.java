package com.crxl.xllxj.module.redDotMessage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crxl.xllxj.module.bag.entity.BagEntity;
import com.crxl.xllxj.module.common.enums.EnumConstant;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.net.CommandId;
import com.crxl.xllxj.module.core.net.MessageChannel;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.ArticleCache;
import com.crxl.xllxj.module.json.datacache.TaskCache;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.redDotMessage.bean.RedDotMessageBean;
import com.crxl.xllxj.module.redDotMessage.entity.RedDotMessageEntity;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.globalgame.auto.json.Article_Json;
import com.globalgame.auto.json.Task_Json;

public class RedDotMessageService extends BaseService{
	
	public RedDotMessageEntity load(String userId,int scope,int specific,int contentId){
		RedDotMessageEntity redDotMessageEntity = redDotMessageDao.findByID(userId + "_" + scope + "_" + specific + "_" + contentId);
		if(redDotMessageEntity == null){
			redDotMessageEntity = new RedDotMessageEntity(userId + "_" + scope + "_" + specific + "_" + contentId,
					userId,scope,specific,contentId,false);
			redDotMessageDao.save(redDotMessageEntity);
		}
		return redDotMessageEntity;
	}

	/**
	 * 查询用户的红点信息
	 */
	public void findRedDotMessageAll(SessionBean session){
		PlayerEntity player = session.getPlayerEntity();
		List<RedDotMessageEntity> redDotMessageEntityList = redDotMessageDao.findRedDotMessageAll(player.getUserId());
		List<RedDotMessageBean> resRedDotMessageList = new ArrayList<RedDotMessageBean>();
		for (RedDotMessageEntity redDotMessageEntity : redDotMessageEntityList) {
			RedDotMessageBean redDotMessageBean = new RedDotMessageBean();
			redDotMessageBean.setScope(redDotMessageEntity.getScope());
			redDotMessageBean.setSpecific(redDotMessageEntity.getSpecific());
			redDotMessageBean.setContentId(redDotMessageEntity.getContentId());
			resRedDotMessageList.add(redDotMessageBean);
		}
		//发送红点信息消息
		ResponseMsg msg = ResponseMsg.createMessage(resRedDotMessageList,CommandId.red_dot_message);
		MessageChannel.sendMessage(session.getChannel(), msg);
	}
	
	/**
	 * 记录红点消息 并且发送给用户
	 * 
	 * @req scope 范围
	 * @req contentId 范围下具体红点id
	 */
	public void recordRedDotMessage(SessionBean session,int scope,int contentId){
		int specific = 0; //范围下具体所属红点
		if(scope == EnumConstant.RedDotMessageScope.appEarance.ordinal()){  //外观下红点
			ArticleCache articleCache = JsonCacheManager.getCache(ArticleCache.class);
			Article_Json article_Json = articleCache.getData(contentId);
			specific = article_Json.getArticleType();  //物品分类
		}
		if(scope == EnumConstant.RedDotMessageScope.task.ordinal()){ //任务下红点
			TaskCache taskCache = JsonCacheManager.getCache(TaskCache.class);
			Task_Json task_Json = taskCache.getData(contentId);
			specific = task_Json.getTaskClassIfy(); //任务展示分类
		}
		RedDotMessageEntity redDotMessageEntity = load(session.getId(),scope,specific,contentId);
		if(scope == EnumConstant.RedDotMessageScope.bag.ordinal()){//如果是背包的
			if(contentId == 10001){ //如果是宝箱
				Map<Integer,Integer> map = session.getBagEntity().getItems();
				if(map.get(contentId) == null){ //如果背包没有了
					redDotMessageEntity.setReadState(true); //消息设置成未读
				} else {
					redDotMessageEntity.setReadState(false); //消息设置成未读
				}
			}
		} else {
			redDotMessageEntity.setReadState(false); //消息设置成未读
		}
		RedDotMessageBean redDotMessageBean = new RedDotMessageBean();
		redDotMessageBean.setScope(redDotMessageEntity.getScope());
		redDotMessageBean.setSpecific(specific);
		redDotMessageBean.setContentId(redDotMessageEntity.getContentId());
		redDotMessageDao.save(redDotMessageEntity);
		//发送红点消息
		ResponseMsg msg = ResponseMsg.createMessage(redDotMessageBean,CommandId.red_dot_message);
		MessageChannel.sendMessage(session.getChannel(), msg);
	}
	
	/**
	 * 用户读取消息红点消息
	 */
	public void userReadRedDotMessage(SessionBean session,int scope,int contentId){
		int specific = 0; //范围下具体所属红点
//		if(scope == EnumConstant.RedDotMessageScope.appEarance.ordinal()){  //外观下红点
//			ArticleCache articleCache = JsonCacheManager.getCache(ArticleCache.class);
//			Article_Json article_Json = articleCache.getData(contentId);
//			specific = article_Json.getArticleType();  //物品分类
//		}
		if(scope == EnumConstant.RedDotMessageScope.task.ordinal()){ //任务下红点
			TaskCache taskCache = JsonCacheManager.getCache(TaskCache.class);
			Task_Json task_Json = taskCache.getData(contentId);
			specific = task_Json.getTaskClassIfy(); //任务展示分类
		}
		RedDotMessageEntity redDotMessageEntity = redDotMessageDao.findByID(session.getId() + "_" + scope + "_" + specific + "_" + contentId);
		if(redDotMessageEntity != null){ //如果有这个红点
			redDotMessageEntity.setReadState(true); //设置红点为已读
			redDotMessageDao.save(redDotMessageEntity);
			RedDotMessageBean redDotMessageBean = new RedDotMessageBean();
			redDotMessageBean.setScope(redDotMessageEntity.getScope());
			redDotMessageBean.setSpecific(specific);
			redDotMessageBean.setContentId(redDotMessageEntity.getContentId());
			//发送消息已读红点消息
			ResponseMsg msg = ResponseMsg.createMessage(redDotMessageBean,CommandId.red_dot_message_read);
			MessageChannel.sendMessage(session.getChannel(), msg);
		}
	}
	
	/**
	 * 外观物品消除红点
	 */
	public void appEaranceReadRedDotMessage(SessionBean session,int scope,int specific){
		List<RedDotMessageEntity> redDotMessageEntityList = redDotMessageDao.findAppEaranceByScopeAndSpecific(session.getId(),scope,specific);
		if(redDotMessageEntityList.size() > 0){
			for (RedDotMessageEntity redDotMessageEntity : redDotMessageEntityList) {
				redDotMessageEntity.setReadState(true);
				redDotMessageDao.save(redDotMessageEntity);
			}
			ResponseMsg msg = ResponseMsg.createMessage(specific,CommandId.red_dot_message_read);
			MessageChannel.sendMessage(session.getChannel(), msg);
		}
		//发送消息已读红点消息
	}
	
}
