package com.crxl.xllxj.module.json.datacache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crxl.xllxj.module.json.JsonCache;
import com.globalgame.auto.json.DropGroup_Json;

public class DropGroupCache extends JsonCache<DropGroup_Json> {
	Map<Integer,List<DropGroup_Json>> map=new HashMap<>();
	@Override
	protected void setDataCache(List<DropGroup_Json> datas) {
		for(DropGroup_Json json:datas){
			List<DropGroup_Json> array=map.get(json.getGroupId());
			if(array==null){
				array=new ArrayList<>();
				map.put(json.getGroupId(), array);
			}
			array.add(json);
		}
	}
	/**
	 * 根据 组号查询 随机项
	 * @param groupId
	 * @return
	 */
	public List<DropGroup_Json>  getByGroupId(int groupId){
		return map.get(groupId);
	}
}
