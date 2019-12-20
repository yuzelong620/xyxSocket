package com.crxl.xllxj.module.room.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.crxl.xllxj.module.json.JsonCacheManager;
import com.crxl.xllxj.module.json.datacache.AnswerCache;
import com.globalgame.auto.json.Answer_Json;

public class RoomBeanUtil {
	public static List<MemberBean> toMemberBean(List<RoomItemBean> items) {
		List<MemberBean> list = new ArrayList<MemberBean>();
		for (RoomItemBean bean : items) {
			list.add(new MemberBean(bean));
		}
		return list;
	} 
}
