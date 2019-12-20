package com.crxl.xllxj.module.json.datacache;

import java.util.HashMap;
import java.util.List;

import com.crxl.xllxj.module.json.JsonCache;
import com.crxl.xllxj.module.player.entity.PlayerEntity;
import com.globalgame.auto.json.Robot_Json;

public class RobotCache extends JsonCache<Robot_Json>{
	HashMap<Integer,PlayerEntity> map=new HashMap<>();
	HashMap<String,PlayerEntity> strMap=new HashMap<>();
	public PlayerEntity  getRobot(int robotId){
		return map.get(robotId);
	}
	
	@Override
	protected void setDataCache(List<Robot_Json> datas) {
		for(Robot_Json json:datas){
			PlayerEntity player=new PlayerEntity();
			player.setGender(json.getGender());
			player.setGoldCoin(0);
			player.setHeadPic(json.getHeadPic());
			player.setNickName(json.getNickName());
			player.setSignature(json.getSignature());
			player.setUserId(json.getId()+"");
			map.put(json.getId(),player);
			strMap.put(player.getUserId(),player);
		}
	}
}
