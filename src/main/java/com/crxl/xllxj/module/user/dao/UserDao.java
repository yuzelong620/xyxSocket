package com.crxl.xllxj.module.user.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.xlcr.mongo.dao.BaseDao;

import com.crxl.xllxj.module.user.entity.UserEntity;

@Repository
public class UserDao extends BaseDao<UserEntity> {
	private static final String _ID = "_id";
	private static final String TOKEN = "token";
	private static final String UNIONID = "unionid";
	private static final String PHONE = "phone";
	private static final String DEVICE_ID = "deviceId";

	/**
	 * 根据机器号获取一个
	 * @param deviceId
	 * @return
	 */
	public UserEntity findByDeviceId(String deviceId) {
		Query query = new Query();
		query.addCriteria(Criteria.where(DEVICE_ID).is(deviceId));
		return super.findOne(query);
	}
    /**
     * 根据手机查询一个
     * @param mobile
     * @return
     */
	public UserEntity findByPhone(String phone){
		Query query = new Query();
		query.addCriteria(Criteria.where(PHONE).is(phone));
		return super.findOne(query); 
	}
	
	 /**
     * 根据unionid查询一个
     * @param mobile
     * @return
     */
	public UserEntity findByUnionid(String unionid){
		Query query = new Query();
		query.addCriteria(Criteria.where(UNIONID).is(unionid));
		return super.findOne(query); 
	}
	
	/**
     * 根据token
     * @param mobile
     * @return
     */
	public UserEntity findByToken(String token){
		Query query = new Query();
		query.addCriteria(Criteria.where(TOKEN).is(token));
		return super.findOne(query); 
	}
	/**
	 * 更新token
	 * @param userId
	 * @param token
	 */
	public void updateUserToken(String userId,String token){
		Query query = new Query();
		query.addCriteria(Criteria.where(_ID).is(userId));
		Update update=new Update();
		update.set(TOKEN,token);
		super.updateFirst(query, update);
	}
	
}
