package com.crxl.xllxj.module.json.datacache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crxl.xllxj.module.json.JsonCache;
import com.globalgame.auto.json.Article_Json;

public class ArticleCache extends JsonCache<Article_Json> {

	Map<Integer, List<Article_Json>> map = new HashMap<Integer, List<Article_Json>>();

	@Override
	protected void setDataCache(List<Article_Json> datas) {
		Map<Integer, List<Article_Json>> temp = new HashMap<Integer, List<Article_Json>>();
		for (Article_Json article_Json : datas) {
			List<Article_Json> list = new ArrayList<Article_Json>();
			if (temp.get(article_Json.getArticleType()) != null) {
				list = temp.get(article_Json.getArticleType());
			}
			list.add(article_Json);
			temp.put(article_Json.getArticleType(), list);
		}
		map = temp;
	}

	public List<Article_Json> getListByArticleType(int articleType) {
		return map.get(articleType);
	}

}
