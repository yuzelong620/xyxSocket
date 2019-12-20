package com.crxl.xllxj.module.json.datacache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.crxl.xllxj.module.json.JsonCache;
import com.globalgame.auto.json.Answer_Json;

public class AnswerCache extends JsonCache<Answer_Json> {
	
	HashMap<Integer, List<Answer_Json>> map = new HashMap<>();
	@Override
	protected void setDataCache(List<Answer_Json> datas) {
		HashMap<Integer, List<Answer_Json>> temp = new HashMap<>();
		for (Answer_Json obj : datas) {
			List<Answer_Json> list = temp.get(obj.getQuestionId());
			if (list == null) {
				list = new ArrayList<Answer_Json>();
				temp.put(obj.getQuestionId(), list);
			}
			list.add(obj);
		}
		this.map = temp;
	}
	
	public List<Answer_Json> getListByQuestionId(int questionId){
		return map.get(questionId);
	}
}
