package com.crxl.xllxj.module.drop.service;

import java.util.List;
import java.util.Map;

import com.crxl.xllxj.module.bag.entity.BagEntity;
import com.crxl.xllxj.module.core.service.BaseService;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.DropGroupCache;
import com.crxl.xllxj.module.session.bean.SessionBean;
import com.crxl.xllxj.module.util.RandomUtil;
import com.globalgame.auto.json.DropGroup_Json;
import com.mind.core.util.StringIntTuple;

public class DropService extends BaseService{
    /**
     * 掉落一个物品
     * @param session
     * @param groupId
     * @return
     */
	public  StringIntTuple randomReward(SessionBean session,int groupId){
		DropGroupCache cache=JsonCacheManager.getCache(DropGroupCache.class);
		List<DropGroup_Json> list=cache.getByGroupId(groupId);
		if(list==null){
			throw new RuntimeException("找不到对应的组号:"+groupId);
		}
		RandomUtil<DropGroup_Json> randomUtil = getRandomUtil(list);
		DropGroup_Json reward=randomUtil.random();
		BagEntity bagEntity = session.getBagEntity();
		Map<Integer, Integer> map = bagEntity.getItems();
		if(!reward.getRewards().getKey().equals("coin")){ 
			if(map.get(Integer.valueOf(reward.getRewards().getKey())) != null){//背包已经有了
				return randomReward(session,groupId);
			}
		}
	    return reward.getRewards();
    }

	private RandomUtil<DropGroup_Json> getRandomUtil(List<DropGroup_Json> list) {
		RandomUtil<DropGroup_Json> randomUtil=new RandomUtil<>();
		for(DropGroup_Json json:list){
			randomUtil.add(json.getOdds(), json);
		}
		return randomUtil;
	}
	
	
	
	
}
