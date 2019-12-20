package com.crxl.xllxj.module.json.datacache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crxl.xllxj.module.json.JsonCache;
import com.crxl.xllxj.module.json.JsonCacheManager;
import com.globalgame.auto.json.AppEarance_Json;
import com.globalgame.auto.json.Article_Json;

public class AppEaranceCache extends JsonCache<AppEarance_Json>{
	
	Map<Integer, List<AppEarance_Json>> map = new HashMap<Integer, List<AppEarance_Json>>();
	
	@Override
	protected void setDataCache(List<AppEarance_Json> datas) {
		Map<Integer, List<AppEarance_Json>> temp = new HashMap<Integer, List<AppEarance_Json>>();
		ArticleCache articleCache = JsonCacheManager.getCache(ArticleCache.class);
		for (AppEarance_Json appEarance_Json : datas) {
			List<AppEarance_Json> list = temp.get(appEarance_Json.getId());
			Article_Json article_Json = articleCache.getData(appEarance_Json.getId());
			if(list==null){
				list = new ArrayList<AppEarance_Json>();
			}
			list.add(appEarance_Json);
			temp.put(article_Json.getArticleType(), list);
		}
		this.map = temp;
	}
	
	public List<AppEarance_Json> getListByArticleType(int articleType){
		return map.get(articleType);
	}
}
