package com.crxl.xllxj.module.json.datacache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.crxl.xllxj.module.json.JsonCache;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.globalgame.auto.json.Answer_Json;
import com.globalgame.auto.json.Question_Json;

public class QuestionCache extends JsonCache<Question_Json>{
	
	HashMap<Integer,List<Question_Json>> map=new HashMap<>();
	@Override
	protected void setDataCache(List<Question_Json> datas) {
		HashMap<Integer,List<Question_Json>> temp=new HashMap<>();
		for(Question_Json obj:datas){
			List<Question_Json> list=temp.get(obj.getVideoId());
			if(list==null){
				list=new ArrayList<Question_Json>();
				temp.put(obj.getVideoId(), list);
			}
			list.add(obj);
		}
		this.map=temp;
	}
	/***
	 * 根据 视频id查找  题目id集合
	 * @param videoId
	 * @return
	 */
	public List<Question_Json> findQuestioinIdByVideoId(int videoId){
		return map.get(videoId);
	}
	
	@Override
	protected void checkData() {
		AnswerCache answerCache=JsonCacheManager.getCache(AnswerCache.class);
		boolean error=false;
		for(Question_Json json:super.getList()){
			List<Answer_Json> list=answerCache.getListByQuestionId(json.getId());
			if(list==null||list.size()<1){
				logger.error("没有答案的问题："+json.getId()+"/"+json.getDesc());
				error=true;
			} 
		}
		if(error){
			throw new RuntimeException("映射答案配置错误");
		}
	}
}
