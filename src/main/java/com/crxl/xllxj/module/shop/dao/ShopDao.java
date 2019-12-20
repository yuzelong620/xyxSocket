package com.crxl.xllxj.module.shop.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.xlcr.mongo.dao.BaseDao;

import com.crxl.xllxj.module.shop.entity.ShopEntity;

public class ShopDao extends BaseDao<ShopEntity>{
	
	public static final String USERID = "userId";

	/**
	 * 根据用户ID查询商品购买信息
	 */
	public List<ShopEntity> findByUserIdBuyData(String userId) {
		Criteria criteria = new Criteria(USERID).is(userId);
		Query query = new Query(criteria);
		return super.find(query);
	}

}
