package com.crxl.xllxj.module.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crxl.xllxj.module.common.enums.EnumConstant;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.core.thread.TaskManager;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.ArticleCache;
import com.crxl.xllxj.module.json.datacache.DuanCache;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.util.DateUtil;
import com.crxl.xllxj.module.util.StringUtil;
import com.globalgame.auto.json.Article_Json;
import com.globalgame.auto.json.Duan_Json;
import com.mind.core.util.StringIntTuple;

public class RewardService extends BaseService {

	private static final String COIN = "coin";
	private boolean isProperty(String key) {
		switch (key) {
		case COIN:
			return true;
		}
		return false;
	}
	
	private boolean isItem(int itemId){
		ArticleCache itemCache=JsonCacheManager.getCache(ArticleCache.class);
		return itemCache.getData(itemId)!=null;
	}

	private boolean checkProperty(SessionBean session, String key, int value) {
		if (COIN.equalsIgnoreCase(key)) {
			return session.getPlayerEntity().getGoldCoin() >= value;
		} 
		else {
			return false;
		}
	}

	/**
	 * 检测资源
	 */
	public boolean check(SessionBean session, int articleId, List<StringIntTuple> stringIntTupleList) {
		for (StringIntTuple sit : stringIntTupleList) {
			if (StringUtil.isNumeric(sit.getKey())) {
				int itemId= Integer.parseInt(sit.getKey());
				if (isItem(itemId)&&checkBagItem(session,itemId,sit.getValue())) {
					continue;
				}
				return false;
			} 
			else if (isProperty(sit.getKey())) {
				if (checkProperty(session, sit.getKey(), sit.getValue())) {
					continue;
				}
				return false;
			} 
			else {
				logger.info("无法识别的资源key:" + sit.getKey());
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 检测限制
	 */
	public boolean checkLimit(SessionBean session, int articleId, List<StringIntTuple> stringIntTupleList) {
		for (StringIntTuple stringIntTuple : stringIntTupleList) {
			PlayerEntity playerEntity = session.getPlayerEntity();
			switch (EnumConstant.RestrictType.find(stringIntTuple.getKey())) {
			case none:
				return true;
			case duan:
				DuanCache duanCache = JsonCacheManager.getCache(DuanCache.class);
				Duan_Json duan_Json = duanCache.getData(playerEntity.getCurrentDuanId());
				if(duan_Json.getClassIfy() >= stringIntTuple.getValue()) { //检测段位
					return true;
				} 
				return false;
			case correct:
				if(playerEntity.getCorrectCount() >= stringIntTuple.getValue()) { //检测答对题
					return true;
				}
				return false;
			case win:
				if(playerEntity.getWinCount() >= stringIntTuple.getValue()) { //检测获胜场数
					return true;
				}
				return false;
			case ranKing:
				return ranKingAward(session,stringIntTuple);
			case time:
				int currentDateInt = DateUtil.getCurrentDateInt(); //获取当前 yyyyMMdd
				if(currentDateInt == stringIntTuple.getValue()){
					return true;
				}
				return false;
			default:
				break;
			}
		}
		return false;
	} 

	private boolean ranKingAward(SessionBean session,StringIntTuple stringIntTuple){
		PlayerEntity playerEntity = session.getPlayerEntity();
		List<PlayerEntity> playerEntityList = playerDao.scoreRanKingList(stringIntTuple.getValue());
		for (PlayerEntity entity : playerEntityList) {
			if(playerEntity == entity) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkBagItem(SessionBean session, int itemId, int value) {
		Map<Integer, Integer> bagItems = session.getBagEntity().getItems();
		Integer num = bagItems.get(itemId);
		if (null == num) {
			return false;
		}
		if (num < value) {
			return false;
		}
		return true;
	}
	public void addResource(SessionBean session, StringIntTuple ... items) {
		ArrayList<StringIntTuple> list=new ArrayList<>();
		for(StringIntTuple item:items){
			list.add(item);
		}
		addResource(session, list);
	}
	/**
	 * 增加资源
	 */
	public void addResource(SessionBean session, List<StringIntTuple> list) {
		boolean bagChange=false;
		boolean playerChange=false;
		for(StringIntTuple sit:list){
			if(StringUtil.isNumeric(sit.getKey())){
				int itemId=Integer.parseInt(sit.getKey());
				if(isItem(itemId)){
					addBagItem(session, itemId, sit.getValue());
					bagChange=true;
					if(sit.getKey().equals("10001")){
						redDotMessageService.recordRedDotMessage(session, EnumConstant.RedDotMessageScope.bag.ordinal(), 
				    			Integer.valueOf(sit.getKey()));
					} else {
						ArticleCache articleCache = JsonCacheManager.getCache(ArticleCache.class);
						Article_Json article_Json = articleCache.getData(Integer.valueOf(sit.getKey()));
						if(article_Json.getArticleType() != 4){ //不是语言包 记录红点
							redDotMessageService.recordRedDotMessage(session, EnumConstant.RedDotMessageScope.appEarance.ordinal(), 
					    			Integer.valueOf(sit.getKey()));
						}
					}
				}
				else{
					logger.error("添加资源发现不可识别的id:"+itemId);
				}
			}
			else if(isProperty(sit.getKey())){
				addProperty(session, sit.getKey(), sit.getValue());
				playerChange=true;
			}
			else  {
				logger.error("不可识别的奖励key:"+sit.getKey());
			}
		}
		if(bagChange){ 
			bagService.sendBagInfo(session);
			TaskManager.instance.saveGroup.hashPut(session.getId().hashCode(),bagDao.getAsynSaveTask(session.getBagEntity()));
		}
		if(playerChange){
			playerService.sendPropertys(session);
			TaskManager.instance.saveGroup.hashPut(session.getId().hashCode(),playerDao.getAsynSaveTask(session.getPlayerEntity()));
		}
	}
	
	/**
	 * 扣除资源
	 */
	public void deductResource(SessionBean session, List<StringIntTuple> stringIntTupleList) {
		boolean bagChange=false;
		boolean playerChange=false;
		for(StringIntTuple sit:stringIntTupleList){
			if(StringUtil.isNumeric(sit.getKey())){
				int itemId=Integer.parseInt(sit.getKey());
				if(isItem(itemId)){
					deductBagItem(session, itemId, sit.getValue());
					bagChange=true;
				}
				else{
					logger.error("添加资源发现不可识别的id:"+itemId);
				}
			}
			if(isProperty(sit.getKey())){
				deductProperty(session, sit.getKey(), sit.getValue());
				playerChange=true;
			}
			else  {
				logger.error("不可识别的奖励key:"+sit.getKey());
			}
		}
		if(bagChange){ 
			bagService.sendBagInfo(session);
			TaskManager.instance.saveGroup.hashPut(session.getId().hashCode(),bagDao.getAsynSaveTask(session.getBagEntity()));
		}
		if(playerChange){
			playerService.sendPropertys(session);
			TaskManager.instance.saveGroup.hashPut(session.getId().hashCode(),playerDao.getAsynSaveTask(session.getPlayerEntity()));
		}
	}
	private void deductProperty(SessionBean session, String key, int value) {
		PlayerEntity player=session.getPlayerEntity();
		switch(key){
			case COIN:
				if(player.getGoldCoin()<value){
					throw new RuntimeException("资源不足");
				}
				player.setGoldCoin(player.getGoldCoin()-value);
				break;
			default:
				logger.error("无法识别的属性类型："+key);
				break;
		}
	}

	private void deductBagItem(SessionBean session, int itemId, int value) {
		Map<Integer,Integer> bag=session.getBagEntity().getItems();
    	Integer num=bag.get(itemId);
    	if(num==null||num<value){
    		throw new RuntimeException("资源不足，请先检查资源是否充足。"); 
    	}
    	num-=value;
    	if(num==0){
    		bag.remove(itemId);
    	}
    	else{
    	    bag.put(itemId, num);
    	}
	}

	/**
	 * 添加物品
	 * @param session
	 * @param itemId
	 * @param value
	 */
    private void addBagItem(SessionBean session,int itemId,int value){
    	Map<Integer,Integer> bag=session.getBagEntity().getItems();
    	Integer num=bag.get(itemId);
    	if(num==null){
    		bag.put(itemId,value);
    		return;
    	}
    	num+=value;
    	bag.put(itemId, num);
    }
	/**
	 * 增加属性
	 */
	private void addProperty(SessionBean session, String key, int value) {
		PlayerEntity player=session.getPlayerEntity();
		if (value < 0) {
			throw new RuntimeException("增加的金币 value错误，不能是负数：" + value);
		}
		if (key.equalsIgnoreCase(COIN)) {
			player.setGoldCoin(player.getGoldCoin() + value);
		} 
		else {
			logger.error("无法识别的货币类型");
		}
	}
}
