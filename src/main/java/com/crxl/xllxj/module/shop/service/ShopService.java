package com.crxl.xllxj.module.shop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.crxl.xllxj.module.bag.entity.BagEntity;
import com.crxl.xllxj.module.core.message.RequestMsg;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.ArticleCache;
import com.crxl.xllxj.module.json.datacache.ShopCache;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.shop.bean.ResShop;
import com.crxl.xllxj.module.shop.entity.ShopEntity;
import com.globalgame.auto.json.Article_Json;
import com.globalgame.auto.json.Shop_Json;

public class ShopService extends BaseService {

	public ShopEntity load(String userId, int articleId) {
		ShopEntity shopEntity = shopDao.findByID(userId + "_" + articleId);
		if (shopEntity == null) {
			shopEntity = new ShopEntity(userId + "_" + articleId, userId, articleId, 0);
			shopDao.save(shopEntity);
		}
		return shopEntity;
	}

	/**
	 * 商店列表
	 */
	public void shopList(RequestMsg req) {
		Integer articleType = req.getBody(Integer.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		ShopCache shopCache = JsonCacheManager.getCache(ShopCache.class);
		BagEntity bagEntity = session.getBagEntity();
		Map<Integer, Integer> map = bagEntity.getItems();
		ArticleCache articleCache = JsonCacheManager.getCache(ArticleCache.class);
		List<Shop_Json> shop_JsonList = shopCache.getList();
		List<ResShop> resShopBeanList = new ArrayList<ResShop>();
		for (Shop_Json shop_Json : shop_JsonList) {
			int obtainCount = 0;
			if(map.get(shop_Json.getArticleId()) != null) {
				obtainCount = obtainCount +1;
			}
			Article_Json article_Json = articleCache.getData(shop_Json.getArticleId());
			if(article_Json.getArticleType().equals(articleType)) {
				resShopBeanList.add(new ResShop(shop_Json.getArticleId(),obtainCount));
			}
		}
		Collections.sort(resShopBeanList, new Comparator<ResShop>() {

			@Override
			public int compare(ResShop o1, ResShop o2) {
				if(o1.getObtainCount() > o2.getObtainCount()) {
					return 1;
				} else if(o1.getObtainCount() == o2.getObtainCount()) {
					if(o1.getArticleId() > o2.getArticleId()) {
						return 1;
					} else if(o1.getArticleId() == o2.getArticleId()) {
						return 0;
					} 
					return -1;
				} 
				return -1;
			}
			
		});
		req.sendCurrentCommand(resShopBeanList);
	}

	/**
	 * 商品购买
	 */
	public void buyGoods(RequestMsg req) {
		Integer articleId = req.getBody(Integer.class);
		SessionBean session = sessionService.getSession(req.getChannel());
		ShopCache shopCache = JsonCacheManager.getCache(ShopCache.class);
		List<Shop_Json> shop_JsonList = shopCache.getList();
		for (Shop_Json shop_Json : shop_JsonList) {
			if (shop_Json.getArticleId().equals(articleId)) {
				if(rewardService.checkLimit(session, articleId, shop_Json.getCondition())) { //检测限制
					boolean flag = rewardService.check(session, articleId, shop_Json.getBuyExpend()); // 检测消耗资源
					if (flag) {
						ShopEntity shopEntity = load(session.getId(),articleId);
						if(shopEntity.getObtainCount() >= shop_Json.getObtainCount()) { //判断购买次数
							req.sendInfoMessage("请勿重复购买");
						} else {
							shopEntity.setObtainCount(shopEntity.getObtainCount() + 1);
							ResShop resShop = new ResShop(articleId,shopEntity.getObtainCount());
							rewardService.deductResource(session, shop_Json.getBuyExpend()); // 扣除资源
							rewardService.addResource(session, shop_Json.getBuyAward()); // 增加资源
							shopDao.save(shopEntity);
							req.sendCurrentCommand(resShop);
						}
						return;
					}
					req.sendInfoMessage("购买失败");
				}
				req.sendInfoMessage("购买条件不足");
			}
		}
	}
	
}
