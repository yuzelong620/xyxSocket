package com.crxl.xllxj.module.json.datacache;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.crxl.xllxj.module.json.JsonCache;
import com.globalgame.auto.json.Duan_Json;

public class DuanCache extends JsonCache<Duan_Json>{
 
	Map<Integer,Duan_Json> downgradingMap=new HashMap<>();//降级映射
	Duan_Json  min;
	Duan_Json  max;
	@Override
	protected void setDataCache(List<Duan_Json> datas) { 
		for(Duan_Json json:datas){ 
			if(min==null||
					json.getId()<min.getId()){
				min=json;
			}
			if(max==null||
					json.getId()>max.getId()){
				max=json;
			}
			if(json.getNextId()>0){//降级映射
				downgradingMap.put(json.getNextId(), json);
			}
		}       
	}
	
	public int getMinId() {
		return min.getId();
	}
	
	public int getMaxId() {
		return max.getId();
	}
	/**
	 * 获取 降级数据
	 * @return
	 */
	public  Duan_Json  getDowngrading(int duanId){
		return downgradingMap.get(duanId);
 	}
	
	
}
