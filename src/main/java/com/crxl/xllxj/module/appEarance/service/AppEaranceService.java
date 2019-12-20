package com.crxl.xllxj.module.appEarance.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.crxl.xllxj.module.appEarance.bean.ItemAdorn;
import com.crxl.xllxj.module.appEarance.bean.ResAppEarance;
import com.crxl.xllxj.module.appEarance.bean.ResBuy;
import com.crxl.xllxj.module.bag.entity.BagEntity;
import com.crxl.xllxj.module.common.enums.EnumConstant;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.AppEaranceCache;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.globalgame.auto.json.AppEarance_Json;

public class AppEaranceService extends BaseService{

	/**
	 * 根据外观物品类型查询所有物品
	 */
	public void findByArticleTypeData(RequestMsg req) {
		Integer articleType = req.getBody(Integer.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		AppEaranceCache appEaranceCache = JsonCacheManager.getCache(AppEaranceCache.class);
		List<AppEarance_Json> appEarance_JsonList = appEaranceCache.getList();
		List<ResAppEarance> resAppEaranceList = new ArrayList<ResAppEarance>();
		BagEntity bagEntity = session.getBagEntity();
		Map<Integer, Integer> map = bagEntity.getItems(); //获取背包物品信息
		for (AppEarance_Json json : appEarance_JsonList) {
			if(json.getItemType().equals(articleType)) { //判断物品类型
				ResAppEarance resAppEarance = new ResAppEarance();
				resAppEarance.setArticleId(json.getId());
				if(map.get(json.getId()) == null) { //背包没有
					if(json.getArticleScope().equals(EnumConstant.AppEarance.none.ordinal())) { //直接获取
						resAppEarance.setState(EnumConstant.ItemState.none.ordinal());
					} 
					if(json.getArticleScope().equals(EnumConstant.AppEarance.needCoin.ordinal())) {
						resAppEarance.setState(EnumConstant.ItemState.attainable.ordinal());
					}
					if(json.getArticleScope().equals(EnumConstant.AppEarance.assignAskAndCoin.ordinal())) { //达到要求在购买
						if(rewardService.checkLimit(session, json.getId(), json.getCondition())) { //判断限制条件是否达成
							resAppEarance.setState(EnumConstant.ItemState.attainable.ordinal());
						} else { //没有达成限制条件
							resAppEarance.setState(EnumConstant.ItemState.none.ordinal());
						}
					}
					if(json.getArticleScope().equals(EnumConstant.AppEarance.assignAsk.ordinal())) { //达到指定需求
						resAppEarance.setState(EnumConstant.ItemState.none.ordinal());
					}
				} else {
					resAppEarance.setState(EnumConstant.ItemState.ownState.ordinal()); //已经拥有
				}
				resAppEarance.setRank(json.getRank());
				resAppEaranceList.add(resAppEarance);
			}
		}
		//消除红点
		redDotMessageService.appEaranceReadRedDotMessage(session, EnumConstant.RedDotMessageScope.appEarance.ordinal(), articleType);
		Collections.sort(resAppEaranceList, new Comparator<ResAppEarance>() {

			@Override
			public int compare(ResAppEarance o1, ResAppEarance o2) {
				if(o1.getState() < o2.getState()) {
					return 1;
				} else if(o1.getState() == o2.getState()){
					if(o1.getRank() > o2.getRank()) {
						return 1;
					} else if(o1.getRank() == o2.getRank()) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
				}
			}
			
		});
		req.sendCurrentCommand(resAppEaranceList);
	}
	
	/**
	 * 物品获取
	 */
	public void getItem(RequestMsg req) {
		Integer articleId = req.getBody(Integer.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		AppEaranceCache appEaranceCache = JsonCacheManager.getCache(AppEaranceCache.class);
		AppEarance_Json appEarance_Json = appEaranceCache.getData(articleId);
		BagEntity bagEntity = session.getBagEntity();
		PlayerEntity playerEntity = session.getPlayerEntity();
		Map<Integer, Integer> map = bagEntity.getItems();
		if(appEarance_Json == null) {
			req.sendInfoMessage("参数错误");
			return;
		}
		if(map.get(articleId) != null) {
			req.sendInfoMessage("已经拥有该物品");
			return;
		}
		if(!rewardService.checkLimit(session, articleId, appEarance_Json.getCondition())) { //检测消耗
			req.sendInfoMessage("没有达到需求");
			return;
		}
		if(!rewardService.check(session, articleId, appEarance_Json.getConsume())) {
			req.sendInfoMessage("资源不足");
			return;
		} 
		rewardService.deductResource(session, appEarance_Json.getConsume()); //扣除资源
		rewardService.addResource(session, appEarance_Json.getAddAward()); //增加资源
		if(appEarance_Json.getItemType().equals(EnumConstant.ItemType.title.ordinal())) { //称号
			playerEntity.setTitle(appEarance_Json.getId());
		}
		if(appEarance_Json.getItemType().equals(EnumConstant.ItemType.headFrame.ordinal())) { //头像框
			playerEntity.setHeadRahmen(appEarance_Json.getId());
		}
		if(appEarance_Json.getItemType().equals(EnumConstant.ItemType.languageBag.ordinal())) { //语言包
			req.sendInfoMessage("功能待定"); //....待定
		}
		playerDao.save(playerEntity);
		req.sendCurrentCommand(new ResBuy(appEarance_Json.getItemType(),appEarance_Json.getId())); //返回获取到的物品
	}
	
	/**
	 * 物品佩戴
	 */
	public void itemAdorn(RequestMsg req) {
		ItemAdorn itemAdorn = req.getBody(ItemAdorn.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		BagEntity bagEntity = session.getBagEntity();
		Map<Integer, Integer> item = bagEntity.getItems();
		PlayerEntity playerEntity = session.getPlayerEntity();
		if(item.get(itemAdorn.getNeedAdorn()) == null) {
			req.sendInfoMessage("请先获得物品");
			return;
		} 
		if(itemAdorn.getArticleType() == EnumConstant.ItemType.headFrame.ordinal()) { //头像框
			playerEntity.setHeadRahmen(itemAdorn.getNeedAdorn());
			req.sendCurrentCommand(playerEntity.getHeadRahmen());
		} 
		if(itemAdorn.getArticleType() == EnumConstant.ItemType.title.ordinal()) { //称号
			playerEntity.setTitle(itemAdorn.getNeedAdorn());
			req.sendCurrentCommand(playerEntity.getTitle());
		} 
		if(itemAdorn.getArticleType() == EnumConstant.ItemType.languageBag.ordinal()) { //语言包
			List<Integer> languageBagList = playerEntity.getLanguageBag();
			for (int i = 0; i < languageBagList.size(); i++) {
				if(languageBagList.get(i).equals(itemAdorn.getAtAdorn())) {
					languageBagList.set(i, itemAdorn.getNeedAdorn());
					playerEntity.setLanguageBag(languageBagList);
					req.sendCurrentCommand(playerEntity.getLanguageBag());
					break;
				}
			}
		}
		playerDao.save(playerEntity);
	}
	
}
