package com.crxl.xllxj.module.redDotMessage.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.xlcr.mongo.dao.BaseDao;

import com.crxl.xllxj.module.redDotMessage.entity.RedDotMessageEntity;

public class RedDotMessageDao extends BaseDao<RedDotMessageEntity>{
	
	private static final String USERID = "userId"; //用户ID
	private static final String READSTATE = "readState"; //读取状态
	private static final String SCOPE = "scope"; //范围
	private static final String SPECIFIC = "specific"; //范围下具体所属红点
	

	/**
	 * 
	 * 根据用户ID查询所有未读的红点消息
	 * 
	 * @param userId 用户ID
	 * @return 所有未读的红点信息
	 */
	public List<RedDotMessageEntity> findRedDotMessageAll(String userId) {
		Criteria criteria = new Criteria(USERID).is(userId).and(READSTATE).is(false);
		Query query = new Query(criteria);
		return super.find(query);
	}

	/**
	 * 查询外观商店下指定物品类型的所有物品
	 * @return 
	 */
	public List<RedDotMessageEntity> findAppEaranceByScopeAndSpecific(String userId, int scope,int specific) {
		Criteria criteria = new Criteria(USERID).is(userId).and(SCOPE).is(scope)
				.and(SPECIFIC).is(specific).and(READSTATE).is(false);
		Query query = new Query(criteria);
		return super.find(query);
	}

}
