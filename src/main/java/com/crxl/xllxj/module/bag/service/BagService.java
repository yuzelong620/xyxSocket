package com.crxl.xllxj.module.bag.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.crxl.xllxj.module.bag.baen.BagItemBean;
import com.crxl.xllxj.module.bag.baen.RewardItem;
import com.crxl.xllxj.module.bag.entity.BagEntity;
import com.crxl.xllxj.module.common.enums.EnumConstant;
import com.crxl.xllxj.module.common.enums.EnumConstant.ItemType;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.message.ResponseMsg;
import com.crxl.xllxj.module.core.net.CommandId;
import com.crxl.xllxj.module.core.net.MessageChannel;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.ArticleCache;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.globalgame.auto.json.Article_Json;
import com.mind.core.util.StringIntTuple; 

 
public class BagService extends BaseService{
	 public BagEntity  load(String userId){
		 BagEntity entity=bagDao.findByID(userId);
		 if(entity==null){
			 entity=new BagEntity(userId, new HashMap<>());
			 bagDao.save(entity);
		 }
		 return entity;
	 }
	 
	public void getBagInfo(RequestMsg req){ 
		SessionBean session=sessionService.getSession(req.getChannel());
		String userId=session.getId();
		BagEntity bag=load(userId);
		List<BagItemBean> itemBeans = new ArrayList<>();
		for (Entry<Integer, Integer> entity : bag.getItems().entrySet()) {
			itemBeans.add(new BagItemBean(entity.getKey(),entity.getValue()));
		}
		req.sendCurrentCommand(itemBeans);
	}
	
	/**
	 * @param bag
	 * @return
	 */
	private List<BagItemBean> toBagItems(Map<Integer,Integer> bag){
		ArrayList<BagItemBean> items=new ArrayList<>(bag.size()); 
		for(Entry<Integer, Integer> entry:bag.entrySet()){
			items.add(new BagItemBean(entry.getKey(), entry.getValue()));
		}
		return items;
	}
	
	public void sendBagInfo(SessionBean session){
		List<BagItemBean> items=toBagItems(session.getBagEntity().getItems());
		ResponseMsg msg=  ResponseMsg.createMessage(items,CommandId.bag_send_info);
		MessageChannel.sendMessage(session.getChannel(), msg);
	}
	
	/**
	 * 获取背包物品
	 */
	public BagEntity getBagAll(SessionBean session){
		return session.getBagEntity();
	}
	/**
	 * 打开宝箱
	 * @param req
	 */
	public void  openBox(RequestMsg req){
		Integer boxId=req.getBody(Integer.class);
	    if(boxId==null){
	    	req.sendErrorMessage("参数错误");
	    	return;
	    }
	    SessionBean session=sessionService.getSession(req.getChannel());
	    BagEntity bag=this.load(session.getId());
	    Integer num=bag.getItems().get(boxId);
	    if(num==null||num<1){
	    	req.sendErrorMessage("宝箱已经用完了");
	    	return;
	    }
	    ArticleCache cache=JsonCacheManager.getCache(ArticleCache.class);
	    Article_Json json=cache.getData(boxId);
	    if(json.getArticleType()!=ItemType.cowryBox.ordinal()){
	    	req.sendErrorMessage("您打开的不是宝箱");
	    	return;
	    }
	    //奖励物品
	    int groupId= json.getValue();
	    StringIntTuple reward= dropService.randomReward(session, groupId);
	    rewardService.addResource(session, reward);
	    if(num == 1){ //最后一个宝箱使用
			//消除消息红点
	    	redDotMessageService.userReadRedDotMessage(session, EnumConstant.RedDotMessageScope.bag.ordinal(),10001);
	    }
	    //消耗道具
	    List<StringIntTuple> stringIntTupleList=new ArrayList<>();
	    stringIntTupleList.add(new StringIntTuple(boxId+"", 1));
	    rewardService.deductResource(session, stringIntTupleList);
	    //发送奖励
	    RewardItem item=new RewardItem(reward.getKey(), reward.getValue());
	    Object[] arr=new Object[]{item};
	    req.sendCurrentCommand(arr);
	}
	
}
