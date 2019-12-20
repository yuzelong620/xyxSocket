package com.crxl.xllxj.module.player.dao;
 
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.xlcr.mongo.dao.BaseDao;

import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.crxl.xllxj.module.player.service.PlayerService; 

@Repository
public class PlayerDao extends BaseDao<PlayerEntity> {
	
	private static final String SCORESUN = "scoreSun";
	private static final String _ID = "_id";
	private static final String DETECTIVEGRADE = "detectiveGrade";

//	public void  updateNpcPlayerInfo(UpUser_Json json) {
//		String userId=json.getId()+"";
//		Criteria criteria= Criteria. where("_id").is(userId);
//		Query query=new Query(criteria);
//		
//		
//		PlayerEntity(userId, json.getNickName(), json.getGoldCoin(), json.getGender(), json.getSignature(),  json.getHeadPic());
//		 
//		
////		String nickName = "";// 昵称 
////		int goldCoin;//金币 
////		int gender;//默认为0 保密1男2女
////		String signature="";//个性签名 默认为空
////		private String headPic;//头像
//		
//		Update update=new Update();
//		update.set("nickName", json.getNickName());
//		update.set("gender", json.getGender());
//		update.set("goldCoin", json.getGoldCoin());
//		update.set("signature", json.getSignature());
//		update.set("headPic", json.getHeadPic());
//		 
//		super.updateFirst(query, update);
//	}
 
	/**
	 * 段位分数排行榜
	 */
	public List<PlayerEntity> scoreRanKingList(int limit) {
		Criteria criteria = new Criteria(SCORESUN).gt(0);
		Query query=new Query(criteria);
		query.with(new Sort(new Order(Direction.DESC,SCORESUN)));
		query.limit(limit);
		return find(query);	
	}
	
	/**
	 * 修改侦探等级
	 */
	public void updateDetecTiveGrade(String userId,int grade) {
		Criteria criteria = new Criteria(_ID).is(userId);
		Query query=new Query(criteria);
		Update update=new Update();
		update.set(DETECTIVEGRADE, grade);
		super.updateInsert(query, update);
	}
	
}
